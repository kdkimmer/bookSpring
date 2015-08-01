package bookshelf.dao;

import bookshelf.model.Book;
import bookshelf.model.Category;

import java.util.List;

/**
 * Created by kim on 7/31/2015.
 */
public interface Dao {

    public List<Book> findAllBooks();

    public List<Category> findAllCategories();

    public List<Book> searchBooksByKeyword(String keyWord);
    
    public List<Book> addBook();
}

