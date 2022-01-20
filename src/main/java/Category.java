public class Category {
    private Integer id;
    private String title;
    private Category category;

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                '}';
    }

    public Category(String title, Category category) {
        this.title = title;
        this.category = category;
    }

    public Category(String title) {
        this.title = title;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category(Integer id, String title, Category category) {
        this.id = id;
        this.title = title;
        this.category = category;
    }
}
