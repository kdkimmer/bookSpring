package bookshelf.model;

import bookshelf.model.Author;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Book {

    private int Id;
    private int categoryId;
    private String publisher;
    private String bookTitle;
    private List<Author> authors;

    public Book(int Id) {
        this.Id = Id;
    }

    public Book(int Id, int categoryId, String publisher, @JsonProperty("bookTitle")String bookTitle) {
        this.Id = Id;
        this.categoryId = categoryId;
        this.publisher = publisher;
        this.bookTitle = bookTitle;

    }
    public Book(@JsonProperty("bookTitle")String bookTitle){
        this.bookTitle = bookTitle;
    }

    public Book() {
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}