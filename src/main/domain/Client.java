package main.domain;

import java.util.Arrays;
import java.util.Comparator;

public class Client extends Person{

    private int clientId;
    private String address;
    private LibraryCard libraryCard;
    private Rent[] rentedBooks;

    public Client(){
    }

    public Client(int clientId, String address, LibraryCard libraryCard, Rent[] rentedBooks) {
        this.clientId = clientId;
        this.address = address;
        this.libraryCard = libraryCard;
        this.rentedBooks = rentedBooks;
    }

    public Client(String firstName, String lastName, int clientId, String address, LibraryCard libraryCard, Rent[] rentedBooks) {
        super(firstName, lastName);
        this.clientId = clientId;
        this.address = address;
        this.libraryCard = libraryCard;
        this.rentedBooks = rentedBooks;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public Rent[] getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(Rent[] rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    public double calculatePenalty(){
        double penalties = 0;
        for(Rent rent:rentedBooks) {
            penalties += rent.getPenalty();
        }
        return penalties;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", address='" + address + '\'' +
                ", libraryCard=" + libraryCard +
                ", rentedBooks=" + Arrays.toString(rentedBooks) +
                '}';
    }

}
