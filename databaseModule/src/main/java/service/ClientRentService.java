package service;

import domain.BookItem;
import domain.Client;
import domain.LibraryCard;
import domain.Rent;
import repository.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClientRentService {

    private final BookItemDatabaseRepository bookItemDatabaseRepository = new BookItemDatabaseRepository();
    private final ClientDatabaseRepository clientDatabaseRepository = new ClientDatabaseRepository();
    private final LibraryCardDatabaseRepository libraryCardDatabaseRepository = new LibraryCardDatabaseRepository();
    private final RentDatabaseRepository rentDatabaseRepository = new RentDatabaseRepository();
    private final LibraryDatabaseRepository libraryDatabaseRepository = new LibraryDatabaseRepository();

    //CLIENT
    public void findClientById(){
        System.out.println("Please enter id of client you're looking for");
        Scanner scanner = new Scanner(System.in);
        int clientId = Integer.parseInt(scanner.nextLine());
        if(clientDatabaseRepository.findOne(clientId) !=null) {
            System.out.println("The client you are searching is :");
            System.out.println(clientDatabaseRepository.findOne(clientId));
        }
        else{
            System.out.println("We have no such client here, sorry");
        }
    }
    public void showClients(){

        if(clientDatabaseRepository.findAll().isEmpty()){
            System.out.println("We have zero clients right now :( ");
        }
        else{
            clientDatabaseRepository.findAll().forEach((k, v)->{
                System.out.println(k + "= " + v);
            });
        }

    }
    public void addNewClient(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating new client");
        Client newClient = new Client();

        String firstName = "", lastName = "", address = "";

        System.out.println("First name : ");
        firstName = scanner.nextLine();

        System.out.println("Last name : ");
        lastName = scanner.nextLine();

        System.out.println("Address : ");
        address = scanner.nextLine();

        newClient.setFirstName(firstName);
        newClient.setLastName(lastName);
        newClient.setAddress(address);

        LibraryCard libraryCard = new LibraryCard();
        libraryCardDatabaseRepository.save(libraryCard);

        newClient.setLibraryCard(libraryCard);

        System.out.println("Please enter the id of library this client belongs to");
        int library_id = Integer.parseInt(scanner.nextLine());

        if(libraryDatabaseRepository.findOne(library_id) == null){
            while((libraryDatabaseRepository.findOne(library_id) == null)){
                System.out.println("This library does not exist!");
                System.out.println("Please enter the id of library this client belongs to");
                library_id = Integer.parseInt(scanner.nextLine());
            }
        }

        System.out.println("The client " + firstName + " " + lastName + " will have a new LibraryCard and an empty rent record\n");
        System.out.println("Do you want to add this client into database?(yes/no)");

        String response = scanner.nextLine();
        if(response.equals("yes")){
            clientDatabaseRepository.saveClient(newClient, library_id);
            System.out.println("Client added successfully");
        }
        else{
            System.out.println("Client not added!");
        }

    }
    public void findClientByFirstName(){
        System.out.println("Please enter first name of client you're looking for");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        Set<Client> clientSet = clientDatabaseRepository.findClientByFirstName(firstName);
        if(clientSet != null){
            System.out.println("Found clients with specified firstname");
            clientSet.forEach(System.out::println);
        }
        else{
            System.out.println("No clients with specified firstname");
        }

    }
    public void findClientByLastName(){
        System.out.println("Please enter last name of client you're looking for");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        Set<Client> clientSet = clientDatabaseRepository.findClientByLastName(lastName);
        if(clientSet != null){
            System.out.println("Found clients with specified lastname");
            clientSet.forEach(System.out::println);
        }
        else{
            System.out.println("No clients with specified lastname");
        }
    }
    public void getClientsWithPenalty(){
        Set<Client> clientSet = new HashSet<>();
        clientDatabaseRepository.findAll().forEach((k, v)-> {
            if (v.calculatePenalty() > 0) {
                clientSet.add(v);
            }
        });

        if (clientSet.size() == 0) {
            System.out.println("We have no client with penalty");
        } else {
            System.out.println("Clients with penalties:");
            clientSet.forEach(System.out::println);
        }

    }
    public void cancelMembershipOfClient(){

        System.out.println("Enter id of client you want to remove from our clients");

        Scanner scanner = new Scanner(System.in);
        int idClient = Integer.parseInt(scanner.nextLine());

        if(clientDatabaseRepository.findOne(idClient) == null){
            System.out.println("Client does not exist here!");
        }
        else{
            System.out.println("Client you want to remove : ");
            System.out.println(clientDatabaseRepository.findOne(idClient));

            System.out.println("Membership of client with id " + idClient + " will be canceled");
            System.out.println("Do you want to proceed?(yes/no)");
            String resp = scanner.nextLine();
            if(resp.equals("yes")){
                clientDatabaseRepository.delete(idClient);
            }
            else{
                System.out.println("Membership was not canceled!");
            }
        }


    }
    public void updateClientData(){
        System.out.println("Please enter id of client you want to edit");
        Scanner scanner = new Scanner(System.in);
        int clientId = Integer.parseInt(scanner.nextLine());
        Client client = clientDatabaseRepository.findOne(clientId);
        if(client == null){
            System.out.println("Client not found");
            while(client == null){
                System.out.println("Please enter id of client you want to edit or press q to quit");
                scanner = new Scanner(System.in);
                String resp = scanner.nextLine();
                if(resp.equals("q")){
                    System.out.println("Abort mission!");
                    return;
                }
                else {
                    clientId = Integer.parseInt(resp);
                    client = clientDatabaseRepository.findOne(clientId);
                }
            }
        } else{
            String firstName = "", lastName = "", address = "";
            LibraryCard libraryCard = new LibraryCard();
            int libraryCardId = libraryCard.getId();
            System.out.println("Client you want to edit: ");
            System.out.println(client);
            System.out.println("Do you want to edit client first name(yes/no)?");
            String resp = scanner.nextLine();
            if(resp.equals("yes")){
                System.out.println("Please enter new first name for client");
                firstName = scanner.nextLine();
            }
            System.out.println("Do you want to edit client last name(yes/no)?");
            String resp1 = scanner.nextLine();
            if(resp1.equals("yes")){
                System.out.println("Please enter new last name for client");
                lastName = scanner.nextLine();
            }
            System.out.println("Do you want to edit client address(yes/no)?");
            String resp2 = scanner.nextLine();
            if(resp2.equals("yes")){
                System.out.println("Please enter new address for client");
                address = scanner.nextLine();
            }

            System.out.println("Do you want to create new library card for client(yes/no)?");
            String resp3 = scanner.nextLine();

            if(resp3.equals("yes")){
                libraryCardDatabaseRepository.save(libraryCard);
                client.setLibraryCard(libraryCard);

            }
            System.out.println("Do you want to proceed with modifying data of client(yes/no)?");
            String resp4 = scanner.nextLine();
            if(resp4.equals("yes")){

                client.setFirstName(firstName);
                client.setLastName(lastName);
                client.setAddress(address);
                clientDatabaseRepository.update(client);
                System.out.println("Client modified");
            }else{
                System.out.println("Canceled operation");
                libraryCardDatabaseRepository.delete(libraryCardId);
            }


        }
    }
    public void addNewRentToClient(){
        System.out.println("Please enter id of client");
        Scanner scanner = new Scanner(System.in);
        int clientId = Integer.parseInt(scanner.nextLine());
        if(clientDatabaseRepository.findOne(clientId) != null) {
            Rent rent = new Rent();
            System.out.println("Please insert id of book_item to borrow");
            int bookItemId = Integer.parseInt(scanner.nextLine());
            BookItem bookItem = bookItemDatabaseRepository.findOne(bookItemId);
            if(bookItem == null){
                while(bookItem == null){
                    System.out.println("We don't have this book item");
                    System.out.println("Please insert id of book_item to borrow");
                    bookItemId = Integer.parseInt(scanner.nextLine());
                    bookItem = bookItemDatabaseRepository.findOne(bookItemId);
                }
            }
            else{
                rent.setBook(bookItem);
                rentDatabaseRepository.saveRent(rent, clientId);
                System.out.println("Rent added for client with id " + clientId);
            }
        }
        else{
            System.out.println("We have no such client here, sorry");
        }
    }
    public void returnBook(){

        System.out.println("Please enter id of client");
        Scanner scanner = new Scanner(System.in);
        int clientId = Integer.parseInt(scanner.nextLine());
        Client client = clientDatabaseRepository.findOne(clientId);
        if(client !=null) {
            System.out.println("Please enter id of book item client want to return");
            int bookItemId = Integer.parseInt(scanner.nextLine());
            boolean ok = false;
            for(Rent rent : client.getRentedBooks()){
                if(rent.getBook().getId() == bookItemId){
                    ok = true;
                    rent.setActualReturnDate(LocalDate.now());
                    rentDatabaseRepository.update(rent);
                    System.out.println("Return date saved");
                }
            }
            if(!ok){
                System.out.println("Client did not borrowed this book");
            }
        }
        else{
            System.out.println("We have no such client here, sorry");
        }
    }

    //RENT
    public void findRentById(){
        System.out.println("Please enter id for rent you're searching:");
        Scanner scanner = new Scanner(System.in);
        int id  = Integer.parseInt(scanner.nextLine());
        if(rentDatabaseRepository.findOne(id) == null){
            System.out.println("Rent not found!");
        }else{
            System.out.println("Rent found: ");
            System.out.println(rentDatabaseRepository.findOne(id));
        }
    }
    public void showRents(){
        if(rentDatabaseRepository.findAll().isEmpty()){
            System.out.println("No rents yet!");
        }else{
            rentDatabaseRepository.findAll().forEach((k, v) ->{
                System.out.println(v);
            });
        }
    }
    public void updateRent(){
    }
    public void removeRent(){
        System.out.println("Please enter id for rent to be removed");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if(rentDatabaseRepository.findOne(id) == null){
            System.out.println("Rent doesn't exost!");
        }else{
            rentDatabaseRepository.delete(id);
            System.out.println("Rent removed!");
        }
    }
    public void findRentsForClient(){}

}
