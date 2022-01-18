import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryRepository {
    private Connection connection = MyConnection.connection;

    public void insert(Category category) {
        try {
            String insert = "insert into category (category_id, title) values (?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, category.getCategory().getId());
            preparedStatement.setString(2, category.getTitle());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);

        }

    }

}
