package repository;

import domain.Author;
import domain.Book;
import domain.Librarian;
import domain.Section;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SectionDatabaseRepository implements Repository<Integer, Section> {

    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    LibrarianDatabaseRepository librarianDatabaseRepository = new LibrarianDatabaseRepository();
    BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository();

    @Override
    public Section findOne(Integer integer) {
        String query = "SELECT * FROM sections WHERE id = ?";
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
    public HashMap<Integer, Section> findAll() {
        HashMap<Integer, Section> sectionHashMap = new HashMap<>();
        String query = "SELECT * FROM sections";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Section section = map(set);
                        sectionHashMap.put(section.getId(), section);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sectionHashMap;
    }

    @Override
    public Section save(Section entity) {
        return null;
    }

    public Section saveSection(Section entity, int library_id) {
        String query = "INSERT INTO sections (name, location, library_id) VALUES (?, ?, ?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getName());
                statement.setString(2, entity.getLocation());
                statement.setInt(3, library_id);
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
    public Section delete(Integer integer) {
        String query = "DELETE FROM sections WHERE id = ? RETURNING *";
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
    public Section update(Section entity) {
        String query = "UPDATE sections set name = ?, location = ? WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getName());
                statement.setString(2, entity.getLocation());
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

    private Section map(ResultSet resultSet) throws SQLException {

        String name = resultSet.getString("name");
        String location = resultSet.getString("location");
        Integer id = resultSet.getInt("id");
        Section section =  new Section();
        section.setId(id);
        section.setName(name);
        section.setLocation(location);

        Set<Librarian> librarianSet = librarianDatabaseRepository.getLibrarians(id);
        section.setLibrarianSet(librarianSet);

        Set<Book> bookSet = bookDatabaseRepository.getSectionBooks(id);
        section.setBookSet(bookSet);
        return section;

    }
}