import java.sql.*;

public class UserRepository {
    private Connection connection = MyConnection.connection;

    public Integer insert(User user) throws SQLException {
        String insert = "insert into \"user\" (role, name, username, password) values (?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getRole().name());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        Integer id = null;
        if (generatedKey.next()) {
            id = generatedKey.getInt(1);
        }
        preparedStatement.close();
        return id;
    }
    public String checkUser(String username,String password) throws SQLException {
        String checkUser = "SELECT * FROM \"user\" " +
                "WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(checkUser);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("role");
        }
        return null;
    }
}