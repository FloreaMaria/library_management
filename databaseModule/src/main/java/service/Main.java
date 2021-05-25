package service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        BookAuthorService bookAuthorService = new BookAuthorService();
        ClientRentService clientRentService = new ClientRentService();

        Scanner scanner = new Scanner(System.in);

        boolean main = true;
        boolean option1 = true;
        do{
            System.out.println("Choose option: ");
            System.out.println("(1) Clients and rents");
            System.out.println("(2) Books and authors");
            System.out.println("(3) Exit");

            int opt = Integer.parseInt(scanner.nextLine());
            switch (opt){
                case 1:
                    do{
                        System.out.println("Choose option: ");
                        System.out.println("(1) Show clients");
                        System.out.println("(2) Add new client");
                        System.out.println("(3) Search client by first name");
                        System.out.println("(4) Search client by last name");
                        System.out.println("(5) Find client by id");
                        System.out.println("(6) Update client data");
                        System.out.println("(7) Cancel membership of client");
                        System.out.println("(8) Return book of client");
                        System.out.println("(9) Add new rent to client");
                        System.out.println("(10) Display penalized clients");
                        System.out.println("(11) Find rent by id");
                        System.out.println("(12) Update rent data");
                        System.out.println("(13) Remove rent");
                        System.out.println("(14) Show rents");
                        System.out.println("(15) Find rents for client");
                        System.out.println("(16) Exit");

                        int option = Integer.parseInt(scanner.nextLine());

                        switch (option){
                            case 1:
                                clientRentService.showClients();
                                break;
                            case 2:
                                clientRentService.addNewClient();
                                break;
                            case 3:
                                clientRentService.findClientByFirstName();
                                break;
                            case 4:
                                clientRentService.findClientByLastName();
                                break;
                            case 5:
                                clientRentService.findClientById();
                                break;
                            case 6:
                                clientRentService.updateClientData();
                                break;
                            case 7:
                                clientRentService.cancelMembershipOfClient();
                                break;
                            case 8:
                                clientRentService.returnBook();
                                break;
                            case 9:
                                clientRentService.addNewRentToClient();
                                break;
                            case 10:
                                clientRentService.getClientsWithPenalty();
                                break;
                            case 11:
                                clientRentService.findRentById();
                                break;
                            case 12:
                                clientRentService.updateRent();
                                break;
                            case 13:
                                clientRentService.removeRent();
                                break;
                            case 14:
                                clientRentService.showRents();
                                break;
                            case 15:
                                clientRentService.findRentsForClient();
                                break;
                            case 16:
                                System.out.println("Leaving...");
                                option1 = false;
                                break;

                        }
                    }while(option1);
                    break;
                case 2:
                    do{
                        System.out.println("Choose option: ");
                        System.out.println("(1) Show authors");
                        System.out.println("(2) Add new author");
                        System.out.println("(3) Search author by full name");
                        System.out.println("(4) Find author by id");
                        System.out.println("(5) Update author");
                        System.out.println("(6) Remove author");
                        System.out.println("(7) Show books");
                        System.out.println("(8) Add new book");
                        System.out.println("(9) Find book by id");
                        System.out.println("(10) Find books by author name");
                        System.out.println("(11) Find book by author id");
                        System.out.println("(12) Find book by title");
                        System.out.println("(13) Show books order asc by title");
                        System.out.println("(14) Show books order desc by title");
                        System.out.println("(15) Find books from specific section");
                        System.out.println("(16) Update book data");
                        System.out.println("(17) Remove book");
                        System.out.println("(18) Exit");

                        int option2 = Integer.parseInt(scanner.nextLine());

                        switch (option2){
                            case 1:
                                bookAuthorService.showAuthors();
                                break;
                            case 2:
                                bookAuthorService.addNewAuthor();
                                break;
                            case 3:
                                bookAuthorService.searchAuthorByName();
                                break;
                            case 4:
                                bookAuthorService.findAuthorById();
                                break;
                            case 5:
                                bookAuthorService.updateAuthorData();
                                break;
                            case 6:
                                bookAuthorService.removeAuthor();
                                break;
                            case 7:
                                bookAuthorService.showBooks();
                                break;
                            case 8:
                                bookAuthorService.addNewBook();
                                break;
                            case 9:
                                bookAuthorService.findBookById();
                                break;
                            case 10:
                                bookAuthorService.findBooksByAuthorName();
                                break;
                            case 11:
                                bookAuthorService.findBooksByAuthorId();
                                break;
                            case 12:
                                bookAuthorService.findBooksByTitle();
                                break;
                            case 13:
                                bookAuthorService.showBooksOrderedByTitleAsc();
                                break;
                            case 14:
                                bookAuthorService.showBooksOrderedByTitleDesc();
                                break;
                            case 15:
                                bookAuthorService.showSectionBooks();
                                break;
                            case 16:
                                bookAuthorService.updateBookData();
                                break;
                            case 17:
                                bookAuthorService.removeBook();
                                break;

                            case 18:
                                System.out.println("Leaving...");
                                option1 = false;
                                break;

                        }
                    }while(option1);
                    break;
                case 3:
                    System.out.println("Leaving...");
                    main = false;
                    break;
            }

        }while(main);


    }
}
