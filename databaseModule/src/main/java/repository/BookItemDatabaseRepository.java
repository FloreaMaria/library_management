package repository;

import domain.Author;
import domain.Book;
import domain.BookItem;

import java.sql.*;
import java.util.HashMap;

public class BookItemDatabaseRepository implements Repository<Integer, BookItem> {
    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    private final AuthorDatabaseRepository authorDatabaseRepository = new AuthorDatabaseRepository();


    @Override
    public BookItem findOne(Integer integer) {
        String query = "SELECT * FROM book_items WHERE book_item_id = ?";
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
    public HashMap<Integer, BookItem> findAll() {
        HashMap<Integer, BookItem> bookItemHashMap= new HashMap<>();
        String query = "SELECT * FROM book_items b LEFT JOIN authors a ON b.author_id = a.id";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        BookItem book = map(set);
                        bookItemHashMap.put(book.getId(), book);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookItemHashMap;
    }

    @Override
    public BookItem save(BookItem entity) {

        String query = "INSERT INTO books (status, pages, length, width, release_year, " +
                "title,publishing_house, category, description, subject, id_author) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getStatus());
                statement.setInt(2, entity.getPages());
                statement.setInt(3, entity.getLength());
                statement.setInt(4, entity.getWidth());
                statement.setInt(5, entity.getReleaseYear());
                statement.setString(6, entity.getTitle());
                statement.setString(7, entity.getPublishingHouse());
                statement.setString(8, entity.getCategory());
                statement.setString(9, entity.getDescription());
                statement.setString(10, entity.getSubject());
                statement.setInt(11, entity.getAuthor().getId());
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
    public BookItem delete(Integer integer) {
        String query = "DELETE FROM book_items WHERE book_item_id = ? RETURNING *";
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
    public BookItem update(BookItem entity) {
        String query = "UPDATE book_items set  status = ?, pages = ?, length = ?, width = ?, release_year = ?, " +
                "title = ?, publishing_house = ?, category = ?, description = ?, subject = ?, author_id = ?" +
                " WHERE book_item_id = ? RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getStatus());
                statement.setInt(2, entity.getPages());
                statement.setInt(3, entity.getLength());
                statement.setInt(4, entity.getWidth());
                statement.setInt(5, entity.getReleaseYear());
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

    private BookItem map(ResultSet resultSet) throws SQLException {

        Integer idBook = resultSet.getInt("book_item_id");
        Integer pages = resultSet.getInt("pages");
        Integer width = resultSet.getInt("width");
        Integer length = resultSet.getInt("length");
        Integer releaseYear = resultSet.getInt("release_year");
        String title = resultSet.getString("title");
        String publishingHouse = resultSet.getString("publishing_house");
        String category  = resultSet.getString("category");
        String description = resultSet.getString("description");
        String subject = resultSet.getString("subject");
        String status = resultSet.getString("status");

        Integer idAuthor = resultSet.getInt("author_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        Author author = new Author();
        author.setId(idAuthor);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        BookItem book = new BookItem(pages, width, length, releaseYear, title, publishingHouse,
                category, description, subject, author, status);
        book.setId(idBook);
        return book;

    }

    private BookItem map2(ResultSet resultSet) throws SQLException {

        Integer idBook = resultSet.getInt("book_item_id");
        Integer pages = resultSet.getInt("pages");
        Integer width = resultSet.getInt("width");
        Integer length = resultSet.getInt("length");
        Integer releaseYear = resultSet.getInt("release_year");
        String title = resultSet.getString("title");
        String publishingHouse = resultSet.getString("publishing_house");
        String category  = resultSet.getString("category");
        String description = resultSet.getString("description");
        String subject = resultSet.getString("subject");
        String status = resultSet.getString("status");

        Integer idAuthor = resultSet.getInt("author_id");

        Author author = authorDatabaseRepository.findOne(idAuthor);
        BookItem bookItem = new BookItem(pages, width, length, releaseYear, title, publishingHouse,
                category, description, subject, author, status);
        bookItem.setId(idBook);
        return bookItem;

    }
}