package repository;

import domain.Author;
import domain.Client;
import domain.Library;
import domain.Section;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LibraryDatabaseRepository implements Repository<Integer, Library>{

    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    ClientDatabaseRepository clientDatabaseRepository = new ClientDatabaseRepository();
    SectionDatabaseRepository sectionDatabaseRepository =  new SectionDatabaseRepository();

    @Override
    public Library findOne(Integer integer) {
        String query = "SELECT * FROM libraries WHERE id = ?";
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
    public HashMap<Integer, Library> findAll() {
        HashMap<Integer, Library> libraryHashMap= new HashMap<>();
        String query = "SELECT * FROM libraries ";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Library library = map(set);
                        libraryHashMap.put(library.getId(), library);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return libraryHashMap;
    }

    @Override
    public Library save(Library entity) {
        String query = "INSERT INTO libraries" +
                " (name, address) VALUES (?, ?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getLibraryName());
                statement.setString(2, entity.getLibraryAddress());
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
    public Library delete(Integer integer) {
        String query = "DELETE FROM libraries WHERE id = ? RETURNING *";
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
    public Library update(Library entity) {
        String query = "UPDATE libraries set name = ?, address = ? WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getLibraryName());
                statement.setString(2, entity.getLibraryAddress());
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

    private Library map(ResultSet resultSet) throws SQLException {

        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        int id = resultSet.getInt("id");

        Library library =  new Library();
        library.setId(id);
        library.setLibraryName(name);
        library.setLibraryAddress(address);
        Set<Client> clients = clientDatabaseRepository.findClientsOfLibrary(id);
        Set<Section> sections = sectionDatabaseRepository.findSectionsOfLibrary(id);
        library.setSectionSet(sections);
        library.setClientSet(clients);

        return library;
    }
}
