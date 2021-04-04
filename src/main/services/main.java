package main.services;

import main.domain.*;
import main.repository.RepositoryClientsCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        MainService mainService = new MainService();
        Scanner scanner = new Scanner(System.in);

        boolean main = true;

        do{
            System.out.println("Choose option : ");
            System.out.println("(1) Show clients");
            System.out.println("(2) Add client");
            System.out.println("(3) Search client by first name");
            System.out.println("(4) Search client by last name");
            System.out.println("(5) Display penalized clients");
            System.out.println("(6) Add book");
            System.out.println("(7) Search book by title");
            System.out.println("(8) Search book by author name");
            System.out.println("(9) Display books ordered by price asc");
            System.out.println("(10) Display books ordered by title");
            System.out.println("(11) Display authors");
            System.out.println("(12) Add author");
            System.out.println("(13) Search books of author");
            System.out.println("(14) Display librarians");
            System.out.println("(15) Add librarian");
            System.out.println("(16) Display total of penalty to collect");
            System.out.println("(17) Exit");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    mainService.showClients();
                    break;
                case 2:
                    mainService.createNewClient();
                    break;
                case 3:

                    System.out.println("Enter first name of the client");
                    String firstname = scanner.nextLine();
                    if(mainService.searchClientByFirstName(firstname).size() == 0){

                        System.out.println("There is no such client here");
                    }
                    else{
                        System.out.println(mainService.searchClientByFirstName(firstname));
                    }
                    break;

                case 4:
                    System.out.println("Enter last name of the client");
                    String lastname = scanner.nextLine();
                    if(mainService.searchClientByLastName(lastname).size() == 0){
                        System.out.println("There is no such client here");
                    }
                    else{
                        System.out.println(mainService.searchClientByFirstName(lastname));
                    }
                    break;

                case 5:

                    System.out.println("We have a total number of " + mainService.getNumberOfPenalizedClients() +
                            "clients with penalties ");
                    mainService.getPenalizedClients();

                case 6:
                    mainService.addBook();
                    break;
                case 7:

                    System.out.println("Enter the title of the book");
                    String title = scanner.nextLine();
                    if(mainService.searchBooksByTitle(title).size()==0){
                        System.out.println("Looks like we don't have a book with that title");
                    }
                    else{
                        for(Book book : mainService.searchBooksByTitle(title)) {
                            System.out.println(book);
                        }
                    }
                    break;

                case 8:

                    System.out.println("Enter first name and last name of author");
                    String auth = scanner.nextLine();
                    if(mainService.searchBooksByAuthorName(auth).size()==0){
                        System.out.println("There is no book having this author");
                    }
                    else{
                        for(Book book: mainService.searchBooksByAuthorName(auth)){
                            System.out.println(book);
                        }
                    }
                    break;

                case 9:
                    mainService.orderBookByPrice();
                    break;
                case 10:
                    mainService.orderBooksByTitle();
                    break;
                case 11:
                    mainService.displayAuthors();
                    break;
                case 12:
                    mainService.addAuthor();
                    break;
                case 13:
                    mainService.searchBooksOfAuthor();
                    break;
                case 14:
                    mainService.displayLibrarians();
                    break;
                case 15:
                    mainService.addLibrarian();
                    break;
                case 16:
                    System.out.println(mainService.collectPenaltyFines());
                    break;
                case 17:
                    System.out.println("Leaving...");
                    main = false;

            }
        }while(main);

    }
}
