package repository;

import domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ClientDatabaseRepository implements Repository<Integer, Client>{
    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    LibraryCardDatabaseRepository libraryCardDatabaseRepository = new LibraryCardDatabaseRepository();
    RentDatabaseRepository rentDatabaseRepository = new RentDatabaseRepository();

    @Override
    public Client findOne(Integer integer) {
        String query = "SELECT * FROM clients WHERE id = ?";
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
    public HashMap<Integer, Client> findAll() {
       HashMap<Integer, Client> clientHashMap = new HashMap<>();
        String query = "SELECT * FROM clients";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Client client = map(set);
                        clientHashMap.put(client.getId(), client);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return clientHashMap;
    }

    @Override
    public Client save(Client entity) {
        return null;
    }

    @Override
    public Client delete(Integer integer) {
        String query = "DELETE FROM clients WHERE id = ? RETURNING *";
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
    public Client update(Client entity) {
        String query = "UPDATE clients set first_name = ?, last_name = ?, address = ?, library_card_id=? WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getLastName());
                statement.setString(3, entity.getAddress());
                statement.setInt(4, entity.getLibraryCard().getId());
                statement.setInt(5, entity.getId());

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


    private Client map(ResultSet resultSet) throws SQLException {

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String address = resultSet.getString("address");
        Integer libraryCardId = resultSet.getInt("library_card_id");
        int id = resultSet.getInt("id");

        Client client = new Client();
        client.setAddress(address);
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);

        LibraryCard card = libraryCardDatabaseRepository.findOne(libraryCardId);
        Rent[] rents = rentDatabaseRepository.findRentsForClient(id).toArray(new Rent[0]);
        client.setLibraryCard(card);
        client.setRentedBooks(rents);
        return client;
    }
    public Client saveClient(Client entity, int id_library) {
        String query = "INSERT INTO clients (first_name, last_name, address, library_card_id, library_id" +
                ") VALUES (?, ?, ?, ?, ?) RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getLastName());
                statement.setString(3, entity.getAddress());
                statement.setInt(4, entity.getLibraryCard().getId());
                statement.setInt(5, id_library);
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

    public Set<Client> findClientByFirstName(String first_name){

        Set<Client> clientHashSet = new HashSet<>();
        String query = "SELECT * FROM clients WHERE UPPER(first_name) = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, first_name.toUpperCase(Locale.ROOT));
                try(ResultSet set = statement.executeQuery()){

                    while(set.next()){
                        Client client = map(set);
                        clientHashSet.add(client);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clientHashSet;
    }
    public Set<Client> findClientByLastName(String last_name){

        Set<Client> clientHashSet = new HashSet<>();
        String query = "SELECT * FROM clients WHERE UPPER(last_name) = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, last_name.toUpperCase(Locale.ROOT));
                try(ResultSet set = statement.executeQuery()){

                    while(set.next()){
                        Client client = map(set);
                        clientHashSet.add(client);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clientHashSet;
    }
    public Set<Client> findClientsOfLibrary(int library_id){
        Set<Client> clientSet = new HashSet<>();
        String query = "SELECT * FROM clients WHERE library_id = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, library_id);
                try(ResultSet set = statement.executeQuery()){

                    while(set.next()){
                        Client client = map(set);
                        clientSet.add(client);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clientSet;
    }
}
