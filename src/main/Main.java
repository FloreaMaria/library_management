package main;

import com.sun.source.tree.NewArrayTree;
import main.domain.*;
import main.repository.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        String auditFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Audit.csv";
        String clientFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Client.csv";
        String rentFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Rent.csv";
        String sectionFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Section.csv";
        String librarianFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Librarian.csv";
        String libraryCardFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\LibraryCard.csv";
        String authorFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Author.csv";

        Repository<Integer, BookItem> bookItemRepository = new InMemoryRepository<>();
        Repository<Integer, Book> bookRepository = new InMemoryRepository<>();
//      Repository<Integer, LibraryCard> libraryCardRepository = new InMemoryRepository<>();
//      Repository<Integer, Librarian> librarianRepository = new InMemoryRepository<>();
//      Repository<Integer, Rent> rentRepository = new InMemoryRepository<>();

        Repository<Integer, Audit> auditRepository = new AuditFileRepository(auditFileName);
        Repository<Integer, Rent> rentRepository = new RentFileRepository(rentFileName, bookItemRepository, auditRepository);
        Repository<Integer, LibraryCard> libraryCardRepository = new LibraryCardFileRepository(auditRepository, libraryCardFileName);
        Repository<Integer, Client> clientRepository = new ClientFileRepository(clientFileName, libraryCardRepository, rentRepository, auditRepository);
        Repository<Integer, Librarian> librarianRepository = new LibrarianFileRepository(librarianFileName, auditRepository);
        Repository<Integer, Section> sectionRepository = new SectionFileRepository(sectionFileName, librarianRepository, auditRepository, bookRepository);
        Repository<Integer, Author> authorRepository  = new AuthorFileRepository(authorFileName, auditRepository, bookRepository);


        LibraryCard libraryCard = new LibraryCard();
        libraryCardRepository.save(libraryCard);

        LibraryCard libraryCard2 = new LibraryCard();
        libraryCardRepository.save(libraryCard2);

        Librarian librarian = new Librarian();
        librarian.setLastName("Ion");
        librarian.setFirstName("Maria");

        Librarian librarian1 = new Librarian();
        librarian1.setFirstName("Ma");
        librarian1.setLastName("Ta");

        librarianRepository.save(librarian);
        librarianRepository.save(librarian1);

        Set<Librarian> librarianSet = new HashSet<>();
        librarianSet.add(librarian);
        librarianSet.add(librarian1);

        Book book = new Book();
        book.setTitle("Maine");

        Book book1 = new Book();
        book1.setTitle("Azi");

        bookRepository.save(book);
        bookRepository.save(book1);
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book);
        bookSet.add(book1);

        Author a = new Author();
        a.setBookArray(bookSet);
        a.setFirstName("Badea");
        a.setLastName("Ion");
        authorRepository.save(a);

        Section s = new Section();
        s.setBookSet(bookSet);
        s.setLibrarianSet(librarianSet);
        sectionRepository.save(s);




//        BookItem bookItem = new BookItem();
//        bookItem.setTitle("Murind cand vine primavara");
//
//        BookItem bookItem1 = new BookItem();
//        bookItem1.setTitle("Cel mai bun");
//
//        bookItemRepository.save(bookItem1);
//        bookItemRepository.save(bookItem);
//
//        Rent rent = new Rent();
//        rent.setBook(bookItem);
//
//        Rent rent1 = new Rent();
//        rent1.setBook(bookItem1);
//
//
//
//        Rent[] rents = new Rent[2];
//        rents[0] = rent;
//        rents[1] = rent1;
//        rentRepository1.save(rent);
//        rentRepository1.save(rent1);
//
//        Client client = new Client();
//        client.setFirstName("Tibi");
//        client.setLastName("Maxim");
//        client.setAddress("Munteni");
//        client.setLibraryCard(libraryCard);
//        client.setRentedBooks(rents);
//        clientRepository.save(client);
//
//
//        Client client1 = new Client();
//        client1.setFirstName("Maria");
//        client1.setLastName("Dascalu");
//        client1.setAddress("MD");
//        client1.setLibraryCard(libraryCard);
//        client1.setRentedBooks(rents);
//        clientRepository.save(client1);



//
//        Rent[] rents = new Rent[2];
//        rents[0] = rent;
//        rents[1] = rent1;
//        client.setRentedBooks(rents);
//
//        clientRepository.save(client);
//        Rent rent1 = new Rent();


       // rent1.setId(2);
//        Rent rent2 = new Rent();
       // rent2.setId(3);

//        LibraryCard libraryCard1 =  new LibraryCard(2, LocalDate.now(), LocalDate.now());
//        libraryCard1.setId(2);
//
//        Repository<Integer, LibraryCard> libraryCardRepository = new InMemoryRepository<>();
//        libraryCardRepository.save(libraryCard);
//        libraryCardRepository.save(libraryCard1);
//
//        Repository<Integer, BookItem> bookItemRepository = new InMemoryRepository<>();
//        BookItem bookItem =  new BookItem();
//        bookItem.setId(1);
//
//        BookItem bookItem1 =  new BookItem();
//        bookItem1.setId(2);
//        bookItemRepository.save(bookItem);
//        bookItemRepository.save(bookItem1);
//
//        Repository<Integer, Rent> rentRepository = new InMemoryRepository<>();
//        rentRepository.save(rent);
//        rentRepository.save(rent1);
//        rentRepository.save(rent2);
//
//        Repository<Integer, Librarian> librarianRepository = new InMemoryRepository<>();
//
//        Librarian librarian = new Librarian();
//        librarian.setId(1);
//        librarianRepository.save(librarian);
//
//        Librarian librarian1 = new Librarian();
//        librarian1.setId(2);
//        librarianRepository.save(librarian1);
//
//        librarianRepository.findAll().forEach(System.out::println);
//
//        String auditFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Audit.csv";
//        String clientFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Client.csv";
//        String rentFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Rent.csv";
//        String sectionFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Section.csv";
//        String librarianFileName = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\data\\Librarian.csv";
//
//
//        Repository<Integer, Audit> auditRepository = new AuditFileRepository(auditFileName);
//        Repository<Integer, Client> clientRepository = new ClientFileRepository(clientFileName, libraryCardRepository, rentRepository, auditRepository);
//        Repository<Integer, Rent> rentRepository1 = new RentFileRepository(rentFileName, bookItemRepository, auditRepository);
//        Repository<Integer, Section> sectionRepository = new SectionFileRepository(sectionFileName, librarianRepository, auditRepository);
//        Repository<Integer, Librarian> librarianRepository1 = new LibrarianFileRepository(librarianFileName, auditRepository);
//
////        clientRepository.findAll().forEach(System.out::println);
////
//        Client client = new Client();
//        client.setFirstName("Tibi");
//        client.setLastName("Maxim");
//        client.setAddress("Munteni");
//        System.out.println(client.getId());
//        LibraryCard librarycard = new LibraryCard();
//        librarycard.setId(3);
//        client.setLibraryCard(libraryCard);
//
//        Rent[] rents = new Rent[2];
//        rents[0] = rent;
//        rents[1] = rent1;
//        client.setRentedBooks(rents);
//
//        clientRepository.save(client);
//
//        clientRepository.findAll().forEach(System.out::println);
//        System.out.println(clientRepository.findOne(1));
//        System.out.println(clientRepository.findOne(2));
//        System.out.println(clientRepository.findOne(3));
//
//
//        rentRepository1.findAll().forEach(System.out::println);

//        Rent rent3 = new Rent();
//
//        BookItem bookItem2 = new BookItem();
//        bookItem2.setId(3);
//        bookItemRepository.save(bookItem2);
//        rent3.setBook(bookItem2);
//        rent3.setRentDate(LocalDate.parse("2021-02-02"));
//        rent3.setActualReturnDate(LocalDate.parse("2021-03-04"));
//        rentRepository1.save(rent3);
//
//        sectionRepository.findAll().forEach(System.out::println);
//        Section section =  new Section("Timisoara", "BLD.2");
//        section.setId(2);
//        Set<Librarian> librarianSet = new HashSet<>();
//        librarianSet.add(librarian);
//        section.setLibrarianSet(librarianSet);
//        sectionRepository.save(section);
//
//        librarianRepository1.findAll().forEach(System.out::println);
//
//        Librarian librarian2 = new Librarian();
//
//        librarian2.setId(3);
//        librarian2.setFirstName("Andrei");
//        librarian2.setLastName("Mocanu");
//        librarian2.setHireDate(LocalDate.now());
//        librarianRepository1.save(librarian2);
//
//        Librarian librarian3 = new Librarian();
//        librarian3.setId(4);
//        librarian3.setFirstName("Ionut");
//        librarian3.setLastName("Mihail");
//        librarian3.setHireDate(LocalDate.now());
//
//        librarianRepository1.save(librarian3);


    }
}
