package bookshelf.service;

import bookshelf.dao.Dao;
import bookshelf.dao.DaoImp;
import bookshelf.model.Author;
import bookshelf.model.Book;
import bookshelf.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class DataController {

    private Dao bookDao = new DaoImp();

   @RequestMapping("/book")
   public List<Book> list() {return bookDao.findBooks();}

    @RequestMapping("/books")
    public List<Book> lists(){return bookDao.findAllBookswithAuthors();}

    @RequestMapping("/bookByID")
    public List<Book> list(String ID){return bookDao.findBookbyID(ID);}

    @RequestMapping("/booksByCategory")
    public List<Book> categoryLists(@RequestParam("Category_Description") String category){return bookDao.findBooksbyCategory(category);}

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

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public Book setNewBook(@RequestBody Book book) {
        bookDao.addBook(book);
        return book;
    }


}