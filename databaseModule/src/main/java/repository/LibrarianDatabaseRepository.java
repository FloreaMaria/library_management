package repository;

import domain.Author;
import domain.Book;
import domain.Librarian;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LibrarianDatabaseRepository implements Repository<Integer, Librarian> {


    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    @Override
    public Librarian findOne(Integer integer) {
        String query = "SELECT * FROM librarians WHERE id = ?";
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
    public HashMap<Integer, Librarian> findAll() {

        String query = "SELECT * FROM librarians";
        HashMap<Integer, Librarian> librarianHashMap= new HashMap<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Librarian librarian = map(set);
                        librarianHashMap.put(librarian.getId(), librarian);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return librarianHashMap;
    }

    @Override
    public Librarian save(Librarian entity) {
        return null;
    }

    @Override
    public Librarian delete(Integer integer) {
        String query = "DELETE FROM librarians WHERE id = ? RETURNING *";
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
    public Librarian update(Librarian entity) {
        String query = "UPDATE librarians set first_name = ?, last_name = ?, hire_date = ? WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getLastName());
                statement.setString(3, entity.getHireDate().toString());
                statement.setInt(4, entity.getId());

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


    private Librarian map(ResultSet resultSet) throws SQLException {

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        LocalDate hireDate = LocalDate.parse(resultSet.getString("hire_date"));
        Integer id = resultSet.getInt("id");
        Librarian librarian =  new Librarian();
        librarian.setId(id);
        librarian.setFirstName(firstName);
        librarian.setLastName(lastName);
        librarian.setHireDate(hireDate);
        return librarian;
    }
    public Librarian saveLibrarian(Librarian entity, int sectionId) {
        String query = "INSERT INTO librarians (first_name, last_name, hire_date, id_section) VALUES (?, ?, ?, ?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getLastName());
                statement.setString(3, entity.getHireDate().toString());
                statement.setInt(4, sectionId
                );
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
    public Set<Librarian> getLibrarians(int section_id){

        Set<Librarian> librarianSet = new HashSet<>();
        String query = "SELECT * FROM librarians WHERE id_section = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, section_id);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        Librarian librarian = map(set);
                        librarianSet.add(librarian);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return librarianSet;
    }

}
