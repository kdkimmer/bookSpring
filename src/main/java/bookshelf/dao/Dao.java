package bookshelf.dao;

import bookshelf.model.Author;
import bookshelf.model.Book;
import bookshelf.model.Category;

import java.util.List;

/**
 * Created by kim on 7/31/2015.
 */
public interface Dao {

    public List<Book> findAllBookswithAuthors();

    public List<Book> findBooks();

    public List<Book> findBookbyID(String ID);

    public List<Book> findBooksbyCategory(String category);

    public List<Author> findALLAuthors();

    public List<Category> findAllCategories();

    public List<Book> searchBooksByKeyword(String keyWord);
    
    public void addBook(Book book);
}

