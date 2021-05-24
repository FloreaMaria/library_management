import domain.*;
import repository.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AuthorDatabaseRepository authorDatabaseRepository = new AuthorDatabaseRepository();
        authorDatabaseRepository.findAll();

        BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository();

//        bookDatabaseRepository.findAll().forEach((key, value) ->{
//            System.out.println(key + " " +  value);
//        });

//        Book book = new Book();
//        Author author = authorDatabaseRepository.findOne(5);
//        book.setAuthor(author);
//
//        bookDatabaseRepository.saveBook(book, 1);
//
//        BookItemDatabaseRepository bookItemDatabaseRepository = new BookItemDatabaseRepository();
//
//        System.out.println(authorDatabaseRepository.findOne(2));
//        BookItem bookItem =  bookItemDatabaseRepository.findOne(3);
//        bookItem.setTitle("titlu nou");
//        bookItemDatabaseRepository.update(bookItem);
//        RentDatabaseRepository rentDatabaseRepository = new RentDatabaseRepository();
//        Rent rent = rentDatabaseRepository.findOne(2);
//        rent.setBook(bookItem);
//
//        rentDatabaseRepository.update(rent);

       // System.out.println(rentDatabaseRepository.findAll());

        SectionDatabaseRepository sectionDatabaseRepository = new SectionDatabaseRepository();
       // System.out.println(sectionDatabaseRepository.findAll());
       // System.out.println(sectionDatabaseRepository.findOne(1));




//        System.out.println(bookItemDatabaseRepository.findAll());
//        System.out.println(bookItemDatabaseRepository.findOne(1));
//        bookItemDatabaseRepository.delete(1);

//        LibrarianDatabaseRepository librarianDatabaseRepository = new LibrarianDatabaseRepository();
//        System.out.println(librarianDatabaseRepository.findOne(1));
//        System.out.println(librarianDatabaseRepository.findAll());

//        Librarian l = new Librarian();
//        l.setHireDate(LocalDate.now());
//        l.setLastName("Costin");
//        l.setFirstName("Badea");
//        librarianDatabaseRepository.save(l);
//        librarianDatabaseRepository.delete(2);


//        System.out.println(authorDatabaseRepository.findAuthorsWithBooks());
//        authorDatabaseRepository.findAll().forEach((k, v)->{
//            System.out.println(k + "= " + v);
//        });


//        System.out.println(authorDatabaseRepository.findAuthorsWithBooks());

//        Book book = new Book(11, 1, 1, 2021, 200, "dadd","asta",
//                "horroh","cea mai carte", "interesant", author);
//
//        book.setId(2);
//        bookDatabaseRepository.save(book);
//        bookDatabaseRepository.update(book);
//
//        LibraryCardDatabaseRepository libraryCardDatabaseRepository = new LibraryCardDatabaseRepository();
//        LibraryCard libraryCard = new LibraryCard();
//        libraryCard.setId(1);
//        libraryCard.setExpireDate(LocalDate.now().plusYears(4));
//
//        System.out.println(libraryCardDatabaseRepository.findAll());
//        libraryCardDatabaseRepository.delete(2);
//        libraryCardDatabaseRepository.update(libraryCard);
//        System.out.println(libraryCardDatabaseRepository.findOne(1));
        //librarianDatabaseRepository.save(l);
       // librarianDatabaseRepository.delete(4);



//        System.out.println(authorDatabaseRepository.findOne(5));
//        System.out.println(authorDatabaseRepository.findAll());
//
//        Author author = new Author();
//        author.setFirstName("Buna");
//        author.setLastName("Vasilica");
//        //authorDatabaseRepository.save(author);
//
//        System.out.println(author = authorDatabaseRepository.findOne(20));
//        author.setLastName("NouVasilica");
//        authorDatabaseRepository.update(author);
////        System.out.println(authorDatabaseRepository.save(author).getId());
//
//        authorDatabaseRepository.delete(20);
//        Book book = new Book();
//        book.setAuthor(authorDatabaseRepository.findOne(21));
//        book.setTitle("carte nou");
//        book.setId(58);
//        bookDatabaseRepository.update(book);
////        bookDatabaseRepository.saveBook(book, 1);
////        System.out.println(bookDatabaseRepository.findAll());



//        BookItemDatabaseRepository bookItemDatabaseRepository = new BookItemDatabaseRepository();
//
//        RentDatabaseRepository rentDatabaseRepository = new RentDatabaseRepository();
//        Rent rent = rentDatabaseRepository.findOne(12);
//        rent.setRentDate(LocalDate.now().plusDays(2));
//        rentDatabaseRepository.update(rent);
//        rentDatabaseRepository.delete(12);

       // rentDatabaseRepository.saveRent(rent, 1);


        ClientDatabaseRepository clientDatabaseRepository = new ClientDatabaseRepository();
        Client client = clientDatabaseRepository.findOne(1);
        client.setFirstName("Ioana");
        clientDatabaseRepository.update(client);
        System.out.println(clientDatabaseRepository.findAll());

    }
}
