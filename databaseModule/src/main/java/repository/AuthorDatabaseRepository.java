package repository;

import domain.Author;
import domain.Book;

import java.sql.*;
import java.util.*;

public class AuthorDatabaseRepository implements Repository<Integer, Author>{

    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    //BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository();
    //private final BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository();

    @Override
    public Author findOne(Integer integer) {

        String query = "SELECT *"+
                " FROM authors a " +
                "LEFT JOIN books b ON a.id = b.id_author where id =?";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, integer);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                       return mapFindAll(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public HashMap<Integer, Author> findAll() {
        HashMap<Integer, Author> authorHashMap= new HashMap<>();
        String query = "SELECT *"+
                " FROM authors a " +
                "LEFT JOIN books b ON a.id = b.id_author";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Author author = mapFindAll(set);
                        authorHashMap.put(author.getId(), author);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return authorHashMap;
    }

    public HashMap<Integer, Author> findAuthorsWithBooks() {
        List<Author> authors = new ArrayList<>();
        HashMap<Integer, Author> authorHashMap =  new HashMap<>();

        String query = "SELECT *"+
                       " FROM authors a " +
                       "LEFT JOIN books b ON a.id = b.id_author";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Author author = mapFindAll(set);
                        if(authorHashMap.containsKey(author.getId())){
                            Author author1 = authorHashMap.get(author.getId());
                            Iterator<Book> bookIterator = author.getBookArray().iterator();
                            while(bookIterator.hasNext()){
                                author1.addBook(bookIterator.next());
                            }

                        }
                        else{
                            authorHashMap.put(author.getId(), author);
                        }
                        authors.add(author);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return authorHashMap;

    }

    @Override
    public Author save(Author entity) {
        String query = "INSERT INTO authors (first_name, last_name) VALUES (?, ?) RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getLastName());
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        return map(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Author delete(Integer integer) {
        String query = "DELETE FROM authors WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, integer);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        return map(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Author update(Author entity) {
        String query = "UPDATE authors set first_name = ?, last_name = ? WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getLastName());
                statement.setInt(3, entity.getId());

                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        return map(set);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Author mapFindAll(ResultSet resultSet) throws SQLException {

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer id = resultSet.getInt("id");
        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        int idBook = resultSet.getInt("id_book");
        int pages = resultSet.getInt("pages");
        int width = resultSet.getInt("width");
        int length = resultSet.getInt("length");
        float price = resultSet.getFloat("price");
        int releaseYear = resultSet.getInt("release_year");
        String title = resultSet.getString("title");
        String publishingHouse = resultSet.getString("publishing_house");
        String category  = resultSet.getString("category");
        String description = resultSet.getString("description");
        String subject = resultSet.getString("subject");

        if(idBook >0){
            Book book = new Book(pages, width, length, releaseYear, price, title, publishingHouse,
                    category, description, subject, author);
            book.setId(idBook);
            author.addBook(book);
        }


        return author;
    }
    private Author map(ResultSet resultSet) throws SQLException {

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer id = resultSet.getInt("id");
        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
//        Set<Book> bookSet = bookDatabaseRepository.getAuthorBooks(id);
//        author.setBookArray(bookSet);

        return  author;
    }

}
