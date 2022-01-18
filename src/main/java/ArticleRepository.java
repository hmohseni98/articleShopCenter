import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private Connection connection = MyConnection.connection;

    public void insert(Article article) {
        String insert = "insert into Article (title, price,category_id,user_id,approved) values (?,?,?,?,?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setInt(2, article.getPrice());
            preparedStatement.setInt(3, article.getCatagory().getId());
            preparedStatement.setInt(4, article.getUser().getId());
            preparedStatement.setBoolean(5, article.getApproved());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void update(Article article) throws SQLException {
        String update = "update Article set title=?, price=? , category_id=?, user_id=? , approved = ? where id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, article.getTitle());
        preparedStatement.setInt(2, article.getPrice());
        preparedStatement.setInt(3, article.getCatagory().getId());
        preparedStatement.setInt(4, article.getUser().getId());
        preparedStatement.setBoolean(5, article.getApproved());
        preparedStatement.setInt(6, article.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(Article article) throws SQLException {
        String delete = " delete  from Article where id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, article.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Article> showArticles() throws SQLException {
        String showArticles = "select * from Article inner join \"user\" u on u.id = Article.user_id inner join category c on Article.category_id = c.id ;\n";
        PreparedStatement preparedStatement = connection.prepareStatement(showArticles);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Article> articles = new ArrayList<Article>();
        while (resultSet.next()) {
            articles.add(new Article(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("price"),
                    new Category(resultSet.getInt("category_id"),
                            resultSet.getString("title"),
                            null),
                    new User(resultSet.getInt("user_id"),
                            resultSet.getString("name"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            Role.valueOf(resultSet.getString("role"))),
                    resultSet.getBoolean("approved")));
        }

        return articles;
    }
}