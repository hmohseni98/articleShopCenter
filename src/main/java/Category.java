public class Category {
    private Integer id;
    private String title;
    private Integer category_id;

    public Category() {
    }

    public Category(Integer id, String title, Integer category_id) {
        this.id = id;
        this.title = title;
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category_id=" + category_id +
                '}';
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

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
