import java.sql.Date;

public class ShoppingCard {
    private Integer id;
    private Date date;
    private Article article;
    private User user;
    private Boolean payed;

    public ShoppingCard() {
    }

    public ShoppingCard(Integer id, Date date, Article article, User user, Boolean payed) {
        this.id = id;
        this.date = date;
        this.article = article;
        this.user = user;
        this.payed = payed;
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "id=" + id +
                ", date=" + date +
                ", article=" + article +
                ", user=" + user +
                ", payed=" + payed +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }
}
