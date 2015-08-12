package bookshelf.dao;

import bookshelf.model.Author;
import bookshelf.model.Book;
import bookshelf.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kim on 7/29/2015.
 */
public class DaoImp implements Dao {
    static{
        //public static void main(java.lang.String[] args) {
        // open    connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load Driver Class");
        }
    }

    private Book book;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore2",
                "bookstoreAdmin", "100000");
    }

    private void closeConnection(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }

    public List<Book> findAllBookswithAuthors() {
        List<Book> result = new ArrayList<Book>();

//        String sql = "select * from book left join author on book.id = author.book_id";

        String sql = " Select * from author a join  book b on b.ID = a.BOOK_ID";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                List<Author> authorList = new ArrayList<Author>();
                Book book = new Book();
                Author author = new Author();
                book.setId(resultSet.getInt("ID"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setCategoryDescription(resultSet.getString("category_description"));
                author.setBookId(resultSet.getInt("book_Id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                authorList.add(author);
                book.setAuthors(authorList);
                result.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }
    public List<Book> findBooks() {
        List<Book> result = new ArrayList<Book>();

        String sql = "select * from book";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setCategoryDescription(resultSet.getString("category_description"));
                result.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public List<Book> findBookbyID(String ID) {
        List<Book> result = new ArrayList<Book>();

        String sql = "select * from book WHERE book.ID = " + ID;

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setCategoryDescription(resultSet.getString("category_description"));
                result.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public List<Book> findBooksbyCategory(String category) {
        List<Book> result = new ArrayList<Book>();

        String sql = "SELECT Category.CATEGORY_DESCRIPTION, Book.book_title\n" +
                "FROM Category\n" +
                "INNER JOIN book\n" +
                "ON Category.CATEGORY_DESCRIPTION=book.CATEGORY_DESCRIPTION\n" +
                "Where (Category.CATEGORY_DESCRIPTION =\'"+category+"\')";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                List<Category> categoryList = new ArrayList<Category>();
                Book book = new Book();
                Category categories = new Category();
                //book.setId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("book_title"));
                //book.setPublisher(resultSet.getString("publisher"));
                book.setCategoryDescription(resultSet.getString("category_description"));
                categories.getDescription();
                categories.setDescription(resultSet.getString("category_description"));
                categoryList.add(categories);
                book.setCategories(categoryList);
                result.add(book);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }
    public List<Book> searchBooksByKeyword(String keyWord) {
        List<Book> result = new ArrayList<Book>();

        String sql = "select * from book left join author on book.id = author.book_id"
                + " where book_title like '%"
                + keyWord.trim()
                + "%'"
                + " or first_name like '%"
                + keyWord.trim()
                + "%'"
                + " or last_name like '%" + keyWord.trim() + "%'";

        Connection connection = null;
        try {

            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                Author author = new Author();
                List<Author> authorList = new ArrayList<Author>();
                book.setId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("book_title"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setBookId(resultSet.getInt("book_id"));
                authorList.add(author);
                book.setAuthors(authorList);
                result.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return result;
    }

    public List<Author> findALLAuthors(){
        List<Author> result = new ArrayList<Author>();
        String sql = "select * from author";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setFirstName(resultSet.getString("First_Name"));
                author.setLastName(resultSet.getString("Last_Name"));
                result.add(author);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public List<Category> findAllCategories() {
        List<Category> result = new ArrayList<>();
        String sql = "select * from category";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setDescription(resultSet.getString("category_description"));
                result.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public void addBook(Book book) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT into BookStore2.book (BOOK_TITLE, CATEGORY_DESCRIPTION, PUBLISHER, PRICE) values (?,?,?,?)");

            statement.setString(1, book.getBookTitle());
            statement.setString(2, book.getCategoryDescription());
            statement.setString(3, book.getPublisher());
            statement.setString(4,book.getPrice());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

//probably do not need a delete book
//    public void delete(Integer bookId) {
//        Connection connection = null;
//
//        try {
//            connection = getConnection();
//            PreparedStatement statement = connection
//                    .prepareStatement("delete from book where id=?");
//            statement.setInt(1, bookId);
//            statement.execute();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            closeConnection(connection);
//        }
//    }
}
