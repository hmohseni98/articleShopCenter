import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection = MyConnection.connection;
    public void insert(User user) throws SQLException {
        String insert = "insert into \"user\" (role, name, username, password) values (?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, user.getRole().name());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.execute();
        preparedStatement.close();
    }

}
