import java.sql.Date;
import java.sql.SQLException;

public class FakeDB {
    public void fillData() throws SQLException {
        User user = new User("writer", "writer", "writer", Role.WRITER);
        User user1 = new User("customer", "customer", "customer", Role.CUSTOMER);
        UserRepository userRepository = new UserRepository();
        user.setId(userRepository.insert(user));
        user1.setId(userRepository.insert(user1));

        Category category = new Category( null,"computer",1);
        Category category1 = new Category(null,"programming",1);
        Category category2 = new Category(null, "network",1);
        CategoryRepository categoryRepository = new CategoryRepository();
        category.setId(categoryRepository.insert(category));
        category1.setId(categoryRepository.insert(category1));
        category2.setId(categoryRepository.insert(category2));


        Article article = new Article("oop", 10000, category1, user, true);
        Article article1 = new Article("spring", 40000, category1, user, true);
        ArticleRepository articleRepository = new ArticleRepository();
        article.setId(articleRepository.insert(article));
        article1.setId(articleRepository.insert(article1));

        ShoppingCard shoppingCard = new ShoppingCard(Date.valueOf("2022-01-12"), article,user1,true);
        ShoppingCard shoppingCard1 = new ShoppingCard(Date.valueOf("2022-11-13"), article1,user1,true);

        ShoppingCardRepository shoppingCardRepository = new ShoppingCardRepository();
        shoppingCard.setId(shoppingCardRepository.insert(shoppingCard));
        shoppingCard1.setId(shoppingCardRepository.insert(shoppingCard1));

    }
}
