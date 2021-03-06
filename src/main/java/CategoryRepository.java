import java.sql.*;

public class CategoryRepository {
    private Connection connection = MyConnection.connection;

    public Integer insert(Category category) throws SQLException {
        String insert = "insert into category (category_id,title) values (?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, category.getCategory_id());
        preparedStatement.setString(2, category.getTitle());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()){
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
    }
    public Category findById(Integer id) throws SQLException{
        String findById = "SELECT * FROM category " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Category category = null;
        if (resultSet.next()){
            category = new Category(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("category_id"));
        }
        return category;
    }
    public void findAll() throws SQLException{
        String findAll = "SELECT * FROM category";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("ID:" + resultSet.getInt("id") + "   " +
                    "Category_ID:" + resultSet.getInt("category_id") + "   " +
                    "Title:" + resultSet.getString("title"));
        }
    }

}
