import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private Connection connection = MyConnection.connection;

    public ArticleRepository() {
    }

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

    public void updateApproved(Integer article_id) throws SQLException{
        String updateApproved = "update Article set approved = true where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateApproved);
        preparedStatement.setInt(1,article_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void updatePrice(Integer article_id,Integer price) throws SQLException{
        String updateApproved = "update Article set price = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateApproved);
        preparedStatement.setInt(1,price);
        preparedStatement.setInt(2,article_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(Integer article_id) throws SQLException {
        String delete = " delete  from Article where id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, article_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Article> showArticles() throws SQLException {
        String showArticles = "select * from Article inner join \"user\" u " +
                "on u.id = Article.user_id inner join category c " +
                "on Article.category_id = c.id ;\n";
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
    public ArticleList showArticlesById(Integer id) throws SQLException {
        String showArticlesById = "select * from Article inner join \"user\" u " +
                "on u.id = Article.user_id inner join category c " +
                "on Article.category_id = c.id " +
                "WHERE u.id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(showArticlesById);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArticleList articles = new ArticleList();
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