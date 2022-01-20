import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private Connection connection = MyConnection.connection;

    public Integer insert(Article article) throws SQLException {
        String insert = "insert into Article (title, price,category_id,user_id,approved) values (?,?,?,?,?);";
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, article.getTitle());
        preparedStatement.setInt(2, article.getPrice());
        preparedStatement.setInt(3, article.getCatagory().getId());
        preparedStatement.setInt(4, article.getUser().getId());
        preparedStatement.setBoolean(5, article.getApproved());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()) {
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
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