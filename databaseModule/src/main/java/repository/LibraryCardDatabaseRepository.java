package repository;

import domain.Librarian;
import domain.Library;
import domain.LibraryCard;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class LibraryCardDatabaseRepository implements Repository<Integer, LibraryCard> {

    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    @Override
    public LibraryCard findOne(Integer integer) {
        String query = "SELECT * FROM library_card WHERE id = ?";
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
    public HashMap<Integer, LibraryCard> findAll() {
        String query = "SELECT * FROM library_card";
        HashMap<Integer, LibraryCard> libraryCardHashMap= new HashMap<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        LibraryCard libraryCard = map(set);
                        libraryCardHashMap.put(libraryCard.getId(), libraryCard);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return libraryCardHashMap;
    }

    @Override
    public LibraryCard save(LibraryCard entity) {
        String query = "INSERT INTO library_card (release_date, expire_date) VALUES (?, ?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getReleaseDate().toString());
                statement.setString(2, entity.getExpireDate().toString());
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
    public LibraryCard delete(Integer integer) {
        String query = "DELETE FROM library_card WHERE id = ? RETURNING *";
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
    public LibraryCard update(LibraryCard entity) {
        String query = "UPDATE library_card set release_date = ?, expire_date = ? WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getReleaseDate().toString());
                statement.setString(2, entity.getExpireDate().toString());
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


    private LibraryCard map(ResultSet resultSet) throws SQLException {

        LocalDate release_date = LocalDate.parse(resultSet.getString("release_date"));
        LocalDate expire_date = LocalDate.parse(resultSet.getString("expire_date"));
        Integer id = resultSet.getInt("id");

        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setId(id);
        libraryCard.setExpireDate(expire_date);
        libraryCard.setReleaseDate(release_date);
        return libraryCard;
    }
}
