import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleShopCenter {
    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepository = new UserRepository();
    static ArticleRepository articleRepository = new ArticleRepository();
    static ShoppingCardRepository shoppingCardRepository = new ShoppingCardRepository();
    static String username ="";
    static String password = "";
    public static void main(String[] args) throws SQLException {
        FakeDB fakeDB = new FakeDB();
        //fakeDB.fillData();
        welcomeMenu();


    }
    static void welcomeMenu() throws SQLException {
        System.out.println("welcome to the Article Shop Center");
        System.out.println("1.login");
        System.out.println("2.register");
        System.out.print("please select one number:");
        Integer numberOfOption = scanner.nextInt();
        if (numberOfOption == 1){
            loginMenu();
        }
        else if(numberOfOption == 2){
            System.out.print("1.enter your last name:");
            String name = scanner.next();
            System.out.print("2.enter your username:");
            String username = scanner.next();
            System.out.print("3.enter your password:");
            String password = scanner.next();
            System.out.print("4.are you writer?[Y/N]");
            String rule = scanner.next();
            if (rule.equals("Y") || rule.equals("y")){
                User user = new User(name,username,password,Role.WRITER);
                user.setId(userRepository.insert(user));
                welcomeMenu();
            }else{
                User user = new User(name,username,password,Role.CUSTOMER);
                user.setId(userRepository.insert(user));
                welcomeMenu();
            }
        }
    }
    static void loginMenu() throws SQLException {
        System.out.print("1.enter your username:");
        username = scanner.next();
        System.out.print("2.enter your password:");
        password = scanner.next();
        checkLogin(username,password);
    }
    static void checkLogin(String username,String password) throws SQLException {
        if (userRepository.checkUser(username,password).equals("WRITER")) {
            System.out.println("dear writer you are login successful!");
            writerMenu();
        }else if (userRepository.checkUser(username,password).equals("CUSTOMER")){
            System.out.println("dear customer you are login successful!");
            customerMenu();
        }else {
            System.out.println("your account not exist!");
            welcomeMenu();
        }
    }
    static void writerMenu() throws SQLException {
        System.out.println("1.submit new article");
        System.out.println("2.approved article");
        System.out.println("3.edit article");
        System.out.println("4.delete article");
        System.out.print("please select one number:");
        Integer numberOfOption = scanner.nextInt();
        if (numberOfOption == 1){
            CategoryRepository categoryRepository = new CategoryRepository();
            categoryRepository.findAll();
            System.out.println("select one category(enter number id):");

        }
    }
    static void customerMenu() throws SQLException {
        System.out.println("1.show article");
        System.out.println("2.add article to shopping card");
        System.out.println("3.Complete the purchase");

        Integer numberOfOption = scanner.nextInt();
        List<Article> articleList = articleRepository.showArticles();
        switch (numberOfOption){
            case 1:
                for (Article rtcl :articleList) {
                    System.out.println(rtcl);
                }
                break;
            case 2 :
                System.out.println("enter atricle : ");
                int articleId = scanner.nextInt();
                Article article = new Article();
                for (int i = 0; i < articleList.size(); i++) {
                    if (articleList.get(i).getId()==articleId){
                        article = articleList.get(i);
                    }
                }

                shoppingCardRepository.insert(new ShoppingCard(
                        Date.valueOf("2022-01-21"),
                        article,
                        userRepository.findByUserPass(username,password),
                        false
                ));
                break;
            case 3:
                List<ShoppingCard> shoppingCardList =shoppingCardRepository.showShoppingCard();
                Integer userId = userRepository.findByUserPass(username,password).getId();
                ShoppingCard shoppingCard = new ShoppingCard();
                for (int i = 0; i < shoppingCardList.size(); i++) {
                    if (shoppingCardList.get(i).getUser().getId()==userId){
                        shoppingCard = shoppingCardList.get(i);
                    }
                }
                shoppingCard.setPayed(true);
                shoppingCardRepository.update(shoppingCard);
                break;
            default:
                System.out.println("wrong number !");
                break;
        }












    }
}
