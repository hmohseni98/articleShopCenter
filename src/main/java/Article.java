public class Article {
    private Integer id;
    private String title;
    private Integer price;
    private Category category;
    private User user;
    private Boolean approved;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", catagory=" + category +
                ", user=" + user +
                ", approved=" + approved +
                '}';
    }

    public Article(String title, Integer price, Category category, User user, Boolean approved) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.user = user;
        this.approved = approved;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCatagory() {
        return category;
    }

    public void setCatagory(Category catagory) {
        this.category = catagory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Article() {
    }

    public Article(Integer id, String title, Integer price, Category catagory, User user, Boolean approved) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = catagory;
        this.user = user;
        this.approved = approved;
    }
}
