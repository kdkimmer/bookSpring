package bookshelf.service;

import bookshelf.model.Author;
import bookshelf.model.Book;
import bookshelf.dao.DaoImp;
import bookshelf.model.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DataController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    DaoImp bookDao = new DaoImp();

   @RequestMapping("/book")
   public List<Book> list() {
       return bookDao.findBooks();
    }

    @RequestMapping("/category")
    public List<Category> categoryList(){
        return bookDao.findAllCategories();
    }

    @RequestMapping("/authors")
    public List<Author> authorList(){
        return bookDao.findALLAuthors();
    }
    @RequestMapping("/keyword")
    public List<Book> searchBooksByKeyword(String keyWord){
        return bookDao.searchBooksByKeyword(keyWord);
    }
}