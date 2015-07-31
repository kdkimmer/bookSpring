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
public class DaoImp {
    static{
        //public static void main(java.lang.String[] args) {
        // open    connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load Driver Class");
        }
    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore",
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

    public List<Book> findAllBooks() {
        List<Book> result = new ArrayList<Book>();
        List<Author> authorList = new ArrayList<Author>();

        String sql = "select * from book inner join author on book.id = author.book_id";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                Author author = new Author();
                book.setId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setCategoryId(resultSet.getInt("category_id"));
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
                book.setCategoryId(resultSet.getInt("category_id"));
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
        List<Author> authorList = new ArrayList<Author>();

        String sql = "select * from book inner join author on book.id = author.book_id"
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
                category.setId(resultSet.getInt("id"));
                category.setDescription(resultSet
                        .getString("category_description"));
                result.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public void insert(Book book) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "insert into Book (book_title) values (?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getBookTitle());
            statement.execute();
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

    public void update(Book book) {
    }

    public void delete(Integer bookId) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("delete from book where id=?");
            statement.setInt(1, bookId);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
