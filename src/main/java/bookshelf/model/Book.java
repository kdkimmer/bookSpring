package bookshelf.model;

import bookshelf.model.Author;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Book {

    private int Id;
    private String categoryDescription;
    private String publisher;
    private String bookTitle;
    private List<Author> authors;
    private List<Category> categories;
    private String price;

    public Book(int Id) {
        this.Id = Id;
    }

    public Book(int Id,String categoryDescription, String publisher,String bookTitle, String price) {
        this.Id = Id;
        this.categoryDescription = categoryDescription;
        this.publisher = publisher;
        this.bookTitle = bookTitle;
        this.price = price;

    }
    public Book(@JsonProperty("bookTitle")String bookTitle){
        this.bookTitle = bookTitle;
    }

    public Book() {
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getId() {
        return Id;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}