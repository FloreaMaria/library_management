package repository;

import domain.Author;
import domain.Book;
import domain.Librarian;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BookDatabaseRepository implements Repository<Integer, Book>{

    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    AuthorDatabaseRepository authorDatabaseRepository = new AuthorDatabaseRepository();

    @Override
    public Book findOne(Integer integer) {
        String query = "SELECT * FROM books WHERE id_book = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, integer);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        return map2(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

//    @Override
//    public HashMap<Integer, Book> findAll() {
//        HashMap<Integer, Book> bookHashMap= new HashMap<>();
//        String query = "SELECT * FROM books b LEFT JOIN authors a ON b.id_author = a.id";
//        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
//            try(PreparedStatement statement = connection.prepareStatement(query)){
//                try(ResultSet set = statement.executeQuery()){
//                    while(set.next()){
//                        Book book = map(set);
//                        bookHashMap.put(book.getId(), book);
//                    }
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return bookHashMap;
//    }
//
//    @Override
//    public Book save(Book entity) {
//        return null;
//    }


    @Override
    public HashMap<Integer, Book> findAll() {
        HashMap<Integer, Book> bookHashMap= new HashMap<>();
        String query = "SELECT id_book, pages, length, width, release_year, price, title," +
                "  publishing_house, category, description, subject, id_author, first_name, last_name" +
                " FROM books b INNER JOIN authors a on b.id_author = a.id";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Book book = map2(set);
                        bookHashMap.put(book.getId(), book);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookHashMap;
    }

    @Override
    public Book save(Book entity) {
        return null;
    }

    public Book saveBook(Book entity, int sectionId) {

        String query = "INSERT INTO books (pages, length, width, release_year, price, " +
                "title,publishing_house, category, description, subject, id_author, id_section) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, entity.getPages());
                statement.setInt(2, entity.getLength());
                statement.setInt(3, entity.getWidth());
                statement.setInt(4, entity.getReleaseYear());
                statement.setDouble(5, entity.getPrice());
                statement.setString(6, entity.getTitle());
                statement.setString(7, entity.getPublishingHouse());
                statement.setString(8, entity.getCategory());
                statement.setString(9, entity.getDescription());
                statement.setString(10, entity.getSubject());
                statement.setInt(11, entity.getAuthor().getId());
                statement.setInt(12, sectionId);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        return map2(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Book delete(Integer integer) {
        String query = "DELETE FROM books WHERE id_book = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, integer);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        return map2(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book update(Book entity) {

        String query = "UPDATE books set pages = ?, length = ?, width = ?, release_year = ?, " +
                "price = ?, title = ?, publishing_house = ?, category = ?, description = ?, subject = ?, id_author = ?" +
                " WHERE id_book = ? RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, entity.getPages());
                statement.setInt(2, entity.getLength());
                statement.setInt(3, entity.getWidth());
                statement.setInt(4, entity.getReleaseYear());
                statement.setDouble(5, entity.getPrice());
                statement.setString(6, entity.getTitle());
                statement.setString(7,entity.getPublishingHouse());
                statement.setString(8, entity.getCategory());
                statement.setString(9, entity.getDescription());
                statement.setString(10, entity.getSubject());
                statement.setInt(11, entity.getAuthor().getId());
                statement.setInt(12, entity.getId());

                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        return map2(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Book map(ResultSet resultSet) throws SQLException {

        Integer idBook = resultSet.getInt("id_book");
        Integer pages = resultSet.getInt("pages");
        Integer width = resultSet.getInt("width");
        Integer length = resultSet.getInt("length");
        Float price = resultSet.getFloat("price");
        Integer releaseYear = resultSet.getInt("release_year");
        String title = resultSet.getString("title");
        String publishingHouse = resultSet.getString("publishing_house");
        String category  = resultSet.getString("category");
        String description = resultSet.getString("description");
        String subject = resultSet.getString("subject");

        Integer idAuthor = resultSet.getInt("id_author");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        Author author = new Author();
        author.setId(idAuthor);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        Book book = new Book(pages, width, length, releaseYear, price, title, publishingHouse,
                category, description, subject, author);
        book.setId(idBook);
        return book;

    }


    private Book map2(ResultSet resultSet) throws SQLException {

        Integer idBook = resultSet.getInt("id_book");
        Integer pages = resultSet.getInt("pages");
        Integer width = resultSet.getInt("width");
        Integer length = resultSet.getInt("length");
        Float price = resultSet.getFloat("price");
        Integer releaseYear = resultSet.getInt("release_year");
        String title = resultSet.getString("title");
        String publishingHouse = resultSet.getString("publishing_house");
        String category  = resultSet.getString("category");
        String description = resultSet.getString("description");
        String subject = resultSet.getString("subject");

        Integer idAuthor = resultSet.getInt("id_author");

        Author author = authorDatabaseRepository.findOne(idAuthor);
        Book book = new Book(pages, width, length, releaseYear, price, title, publishingHouse,
                category, description, subject, author);
        book.setId(idBook);
        return book;

    }

    public Set<Book> getSectionBooks(int section_id){
        Set<Book> bookSet = new HashSet<>();
        String query = "SELECT * FROM books b JOIN authors a on b.id_author = a.id WHERE id_section = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, section_id);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        Book book = map(set);
                        bookSet.add(book);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookSet;
    }

    public Set<Book> getAuthorBooks(int id_author){
        Set<Book> bookSet = new HashSet<>();
        String query = "SELECT * FROM books WHERE id_author = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id_author);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        Book book = map(set);
                        bookSet.add(book);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookSet;
    }
}
