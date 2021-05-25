package service;

import domain.*;
import repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Service {

    private final AuthorDatabaseRepository authorDatabaseRepository = new AuthorDatabaseRepository();
    private final BookItemDatabaseRepository bookItemDatabaseRepository = new BookItemDatabaseRepository();
    private final BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository();
    private final ClientDatabaseRepository clientDatabaseRepository = new ClientDatabaseRepository();
    private final LibrarianDatabaseRepository librarianDatabaseRepository =  new LibrarianDatabaseRepository();
    private final LibraryCardDatabaseRepository libraryCardDatabaseRepository = new LibraryCardDatabaseRepository();
    private final RentDatabaseRepository rentDatabaseRepository = new RentDatabaseRepository();
    private final SectionDatabaseRepository sectionDatabaseRepository = new SectionDatabaseRepository();
    private final LibraryDatabaseRepository libraryDatabaseRepository = new LibraryDatabaseRepository();


    //LIBRARIAN
    public void addNewLibrarian(){

        System.out.println("Creating new librarian...");
        System.out.println("Please enter first name:");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();

        System.out.println("Please enter last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Please enter hire_date(yyyy-mm-dd):");
        String hireDate = scanner.nextLine();

        System.out.println("Please enter section where this librarian will work");
        int sectionId = Integer.parseInt(scanner.nextLine());

        Librarian librarian = new Librarian();
        librarian.setFirstName(firstName);
        librarian.setLastName(lastName);
        librarian.setHireDate(LocalDate.parse(hireDate));
        librarianDatabaseRepository.saveLibrarian(librarian, sectionId);
        System.out.println("Librarian added successfully!");

    }
    public void showLibrarians(){
        if(librarianDatabaseRepository.findAll().isEmpty()){
            System.out.println("We have no librarians working here!");
        }
        else{
            librarianDatabaseRepository.findAll().forEach((k, v)->{
                System.out.println(v);
            });
        }
    }
    public void findLibrarian(){
        System.out.println("Please enter id of librarian you're looking for:");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if(librarianDatabaseRepository.findOne(id) == null){
            System.out.println("We don't have this librarian");
        }
        else{
            System.out.println("You're looking for..");
            System.out.println(librarianDatabaseRepository.findOne(id));
        }
    }
    public void updateLibrarianData(){
        System.out.println("Please enter id of librarian you want to edit");
        Scanner scanner = new Scanner(System.in);
        int librarianId = Integer.parseInt(scanner.nextLine());
        Librarian librarian = librarianDatabaseRepository.findOne(librarianId);
        if(librarian == null){
            System.out.println("Librarian not found");
            while(librarian == null){
                System.out.println("Please enter id of librarian you want to edit or press q to quit");
                scanner = new Scanner(System.in);
                String resp = scanner.nextLine();
                if(resp.equals("q")){
                    System.out.println("Abort mission!");
                    return;
                }
                else {
                    librarianId = Integer.parseInt(resp);
                    librarian = librarianDatabaseRepository.findOne(librarianId);
                }
            }
        }
        else{
            String firstName = "", lastName = "", hireDate = "";

            System.out.println("Librarian you want to edit: ");
            System.out.println(librarian);
            System.out.println("Do you want to edit librarian first name(yes/no)?");
            String resp = scanner.nextLine();
            if(resp.equals("yes")){
                System.out.println("Please enter new first name for librarian");
                firstName = scanner.nextLine();
            }
            System.out.println("Do you want to edit librarian last name(yes/no)?");
            String resp1 = scanner.nextLine();
            if(resp1.equals("yes")){
                System.out.println("Please enter new last name for librarian");
                lastName = scanner.nextLine();
            }


            System.out.println("Do you want to proceed with modifying data of librarian(yes/no)?");
            String resp4 = scanner.nextLine();
            if(resp4.equals("yes")){
                librarian.setFirstName(firstName);
                librarian.setLastName(lastName);
                librarianDatabaseRepository.update(librarian);
                System.out.println("Operation succeeded");
            }
            else{
                System.out.println("Operation canceled..");
            }
        }
    }
    public void removeLibrarian(){
        System.out.println("Please enter id of librarian to be removed");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        if(librarianDatabaseRepository.findOne(id) == null){
            System.out.println("Librarian does not exist here!");
        }
        else{
            librarianDatabaseRepository.delete(id);
            System.out.println("Operation succeeded");
        }
    }


}
