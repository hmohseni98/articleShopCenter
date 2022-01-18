import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCardRepository {
    private Connection connection = MyConnection.connection;

    public void insert(ShoppingCard shoppingCard) throws SQLException {
        String insert = "insert into shopping_card (article_id, user_id,date,payed) values (?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, shoppingCard.getArticle().getId());
        preparedStatement.setInt(2, shoppingCard.getUser().getId());
        preparedStatement.setDate(3, shoppingCard.getDate());
        preparedStatement.setBoolean(4, shoppingCard.getPayed());
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void update(ShoppingCard shoppingCard) throws SQLException {
        String update = "update shopping_card set payed=? where id = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setBoolean(1, shoppingCard.getPayed());
        preparedStatement.setInt(2, shoppingCard.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public List<ShoppingCard> showShoppingCard() throws SQLException {
        String showShoppingCard = "select * from shopping_card " +
                "inner join Article A on A.id = shopping_card.article_id " +
                "inner join \"user\" u on u.id = A.user_id " +
                "inner join category c on c.id = A.category_id  where payed = false;";
        PreparedStatement preparedStatement = connection.prepareStatement(showShoppingCard);
        ResultSet resultSet =  preparedStatement.executeQuery();

        List<ShoppingCard> shoppingCards = new ArrayList<ShoppingCard>();
        while (resultSet.next()) {
            shoppingCards.add(new ShoppingCard(resultSet.getInt("id"),
                    resultSet.getDate("date"),
                    new Article(resultSet.getInt("id"),
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
                            resultSet.getBoolean("approved")),
                            new User(resultSet.getInt("user_id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    Role.valueOf(resultSet.getString("role"))),
                    resultSet.getBoolean("payed")));
        }
        preparedStatement.close();
        return shoppingCards;


    }
}
