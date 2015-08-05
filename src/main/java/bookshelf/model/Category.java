package bookshelf.model;

/**
 * Created by kim on 7/29/2015.
 */
public class Category {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" + ", Description: '" + description + '\'' +
                '}';
    }
}
