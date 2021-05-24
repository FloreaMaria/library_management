package repository;

import domain.Book;
import domain.BookItem;
import domain.LibraryCard;
import domain.Rent;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RentDatabaseRepository implements Repository<Integer, Rent>{

    private static final String DATABASE_PATH = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    BookItemDatabaseRepository bookItemDatabaseRepository = new BookItemDatabaseRepository();

    @Override
    public Rent findOne(Integer integer) {
        String query = "SELECT * FROM rents WHERE id = ?";
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
    public HashMap<Integer, Rent> findAll() {
        String query = "SELECT * FROM rents";
        HashMap<Integer, Rent> rentHashMap= new HashMap<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                try(ResultSet set = statement.executeQuery()){
                    while(set.next()){
                        Rent rent = map(set);
                        rentHashMap.put(rent.getId(), rent);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rentHashMap;
    }

    @Override
    public Rent save(Rent entity) {
        String query = "INSERT INTO rents (penalty, rent_date, actual_return_date, book_item_id) VALUES (?, ?, ?, ?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setDouble(1, entity.getPenalty());
                statement.setString(2, entity.getRentDate().toString());
                if(entity.getActualReturnDate() != null){
                    statement.setString(3, entity.getActualReturnDate().toString());
                }
                else{
                    statement.setString(3, "");
                }
                statement.setInt(4, entity.getBook().getId());
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
    public Rent delete(Integer integer) {
        String query = "DELETE FROM rents WHERE id = ? RETURNING *";
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
    public Rent update(Rent entity) {
        String query = "UPDATE rents set penalty = ?, rent_date = ?, actual_return_date = ?, book_item_id = ? WHERE id = ? RETURNING *";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setDouble(1, entity.getPenalty());
                statement.setString(2, entity.getRentDate().toString());
                if(entity.getActualReturnDate() != null){
                    statement.setString(3, entity.getActualReturnDate().toString());
                }
                else{
                    statement.setString(3, "");
                }
                statement.setInt(4, entity.getBook().getId());
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
    private Rent map(ResultSet resultSet) throws SQLException {

        Integer id = resultSet.getInt("id");
        double penalty = resultSet.getDouble("penalty");
        LocalDate actual_return_date = null;
        LocalDate rent_date = LocalDate.parse(resultSet.getString("rent_date"));
        if(!resultSet.getString("actual_return_date").equals("")){
            actual_return_date = LocalDate.parse(resultSet.getString("actual_return_date"));
        }

        int book_item_id = resultSet.getInt("book_item_id");

        Rent rent = new Rent();
        rent.setId(id);
        rent.setRentDate(rent_date);
        rent.setActualReturnDate(actual_return_date);
        rent.setPenalty(penalty);

        BookItem bookItem = bookItemDatabaseRepository.findOne(book_item_id);
        rent.setBook(bookItem);

        return rent;
    }

    public Rent saveRent(Rent entity, int clientId) {
        String query = "INSERT INTO rents (penalty, rent_date, actual_return_date, book_item_id, client_id) VALUES (?, ?, ?, ?, ?) RETURNING *";

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setDouble(1, entity.getPenalty());
                statement.setString(2, entity.getRentDate().toString());
                if(entity.getActualReturnDate() != null){
                    statement.setString(3, entity.getActualReturnDate().toString());
                }
                else{
                    statement.setString(3, "");
                }
                statement.setInt(4, entity.getBook().getId());
                statement.setInt(5, clientId);
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

    public ArrayList<Rent> findRentsForClient(int clientId){
        ArrayList<Rent> rents= new ArrayList<>();
        String query = "SELECT * FROM rents WHERE client_id = ?";
        try(Connection connection = DriverManager.getConnection(DATABASE_PATH, USER, PASS)){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, clientId);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        Rent rent= map(set);
                        rents.add(rent);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rents;
    }

}
