import java.sql.Date;
import java.sql.SQLException;

public class ArticleShopCenter {
    public static void main(String[] args) throws SQLException {
        User user = new User(null, "A", "a", "A", Role.WRITER);


        Category category = new Category( "C");
        ShoppingCard shoppingCard = new ShoppingCard();
        Article article = new Article("D", 1000, category, user, true);
        User user1 = new User("S", "b", "B", Role.CUSTOMER);

        UserRepository userRepository = new UserRepository();
        userRepository.insert(user);
        userRepository.insert(user1);
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.insert(category);
        category.setId(1);
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.insert(article);

        ShoppingCardRepository shoppingCardRepository = new ShoppingCardRepository();
        //shoppingCardRepository.insert(new ShoppingCard( Date.valueOf("2022-11-01"), article,user1,true ));


        //Integer id, Date date, Article article, User user, Boolean payed
        //  public Article(Integer id, String title, Integer price, Category catagory, User user, Boolean approved) {

    }
}
