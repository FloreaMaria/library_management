package domain;

import java.util.Arrays;

public class Client extends Person {

    private String address;
    private LibraryCard libraryCard;
    private Rent[] rentedBooks;
    private static int rentId = 0;

    public Client(){
        rentId++;
        this.setId(rentId);
    }

    public Client(String address, LibraryCard libraryCard, Rent[] rentedBooks) {
        this.address = address;
        this.libraryCard = libraryCard;
        this.rentedBooks = rentedBooks;
        rentId++;
        this.setId(rentId);
    }

    public Client(String firstName, String lastName,String address, LibraryCard libraryCard, Rent[] rentedBooks) {
        super(firstName, lastName);
        this.address = address;
        this.libraryCard = libraryCard;
        this.rentedBooks = rentedBooks;
        rentId++;
        this.setId(rentId);
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
        for(Rent rent : rentedBooks) {
            penalties += rent.getPenalty();
        }
        return penalties;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + getId() +
                ", firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", address='" + address + '\'' +
                ", libraryCard=" + libraryCard +
                ", rentedBooks=" + Arrays.toString(rentedBooks) +
                '}';
    }

}
