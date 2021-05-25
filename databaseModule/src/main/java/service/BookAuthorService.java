package service;

import domain.Author;
import domain.Book;
import repository.*;

import java.util.Scanner;
import java.util.Set;

public class BookAuthorService {

    private final AuthorDatabaseRepository authorDatabaseRepository = new AuthorDatabaseRepository();
    private final BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository();
    private final SectionDatabaseRepository sectionDatabaseRepository = new SectionDatabaseRepository();

    //AUTHOR
    public void findAuthorById(){
        System.out.println("Please enter id of author you're looking for");
        Scanner scanner = new Scanner(System.in);
        int authorId = Integer.parseInt(scanner.nextLine());
        if(authorDatabaseRepository.findOne(authorId) != null) {
            System.out.println("The author you are searching is :");
            System.out.println(authorDatabaseRepository.findOne(authorId));
        }
        else{
            System.out.println("We have no such author here, sorry");
        }
    }
    public void showAuthors(){
        if(authorDatabaseRepository.findAll().isEmpty()){
            System.out.println("We have zero authors right now :( ");
        }
        else{
            authorDatabaseRepository.findAll().forEach((k, v)->{
                System.out.println(k + "= " + v);
            });
        }
    }
    public void addNewAuthor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating new author");
        String firstName = "", lastName = "";

        System.out.println("First name : ");
        firstName = scanner.nextLine();
        System.out.println("Last name : ");
        lastName = scanner.nextLine();
        Set<Author> authors = authorDatabaseRepository.findAuthorByName(firstName, lastName);
        if(authors.size() > 0){
            System.out.println("Author already in database");
        }
        else{
            Author author = new Author();
            author.setLastName(lastName);
            author.setFirstName(firstName);
            System.out.println("Author will be added with empty array of books");
            System.out.println("Do you want do proceed(yes/no)?");
            String resp = scanner.nextLine();
            if(resp.equals("yes")){
                System.out.println("Author added successfully");
                authorDatabaseRepository.save(author);
            }
            else{
                System.out.println("Canceling operation...");
            }
        }


    }
    public void searchAuthorByName(){
        System.out.println("Please enter name of author you're looking for");
        Scanner scanner = new Scanner(System.in);
        System.out.println("first name:");
        String firstName = scanner.nextLine();
        System.out.println("last name :");
        String lastName = scanner.nextLine();
        Set<Author> authors = authorDatabaseRepository.findAuthorByName(firstName, lastName);
        if(authors.size() == 0){
            System.out.println("We have no author with those credentials");
        }
        else{
            System.out.println("Authors found:");
            authors.forEach(System.out::println);
        }


    }
    public void updateAuthorData(){
        System.out.println("Please enter id of author you want to edit");
        Scanner scanner = new Scanner(System.in);
        int authorId = Integer.parseInt(scanner.nextLine());
        Author author = authorDatabaseRepository.findOne(authorId);
        if(author == null){
            System.out.println("Author not found");
            while(author == null){
                System.out.println("Please enter id of author you want to edit or press q to quit");
                scanner = new Scanner(System.in);
                String resp = scanner.nextLine();
                if(resp.equals("q")){
                    System.out.println("Abort mission!");
                    return;
                }
                else {
                    authorId = Integer.parseInt(resp);
                    author = authorDatabaseRepository.findOne(authorId);
                }
            }
        }
        else{
            String firstName = "", lastName = "";

            System.out.println("Author you want to edit: ");
            System.out.println(author);
            System.out.println("Do you want to edit author first name(yes/no)?");
            String resp = scanner.nextLine();
            if(resp.equals("yes")){
                System.out.println("Please enter new first name for author");
                firstName = scanner.nextLine();
            }
            System.out.println("Do you want to edit author last name(yes/no)?");
            String resp1 = scanner.nextLine();
            if(resp1.equals("yes")){
                System.out.println("Please enter new last name for author");
                lastName = scanner.nextLine();
            }
            System.out.println("Do you want to proceed with modifying data of author(yes/no)?");
            String resp4 = scanner.nextLine();
            if(resp4.equals("yes")){
                author.setFirstName(firstName);
                author.setLastName(lastName);
                authorDatabaseRepository.update(author);
                System.out.println("Operation succeeded");
            }
            else{
                System.out.println("Operation canceled..");
            }
        }
    }
    public void removeAuthor(){
        System.out.println("Please insert id of author you want to remove");
        Scanner scanner =  new Scanner(System.in);
        int authorId = Integer.parseInt(scanner.nextLine());
        if(authorDatabaseRepository.findOne(authorId) == null){
            System.out.println("We don't have this author");
        }
        else{
            System.out.println("Author you want to remove:");
            System.out.println(authorDatabaseRepository.findOne(authorId));
            System.out.println("Do you want to proceed(yes/no)?");
            String resp = scanner.nextLine();
            if(resp.equals("yes")){
                authorDatabaseRepository.delete(authorId);
                System.out.println("Author removed!");
            }
            else{
                System.out.println("Operation canceled!");
            }
        }

    }


    //BOOK
    public void addNewBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating new book");

        String title = "", publishingHouse = "", category = "";
        String description = "", subject = "";
        int pages, length, width, releaseYear, authorId, sectionId;

        System.out.println("title: ");
        title = scanner.nextLine();

        System.out.println("author id: ");
        authorId = Integer.parseInt(scanner.nextLine());

        System.out.println("section id: ");
        sectionId = Integer.parseInt(scanner.nextLine());

        System.out.println("release year :");
        releaseYear = Integer.parseInt(scanner.nextLine());

        System.out.println("publishing House: ");
        publishingHouse = scanner.nextLine();

        System.out.println("description: ");
        description = scanner.nextLine();

        System.out.println("subject: ");
        subject = scanner.nextLine();

        System.out.println("category: ");
        category = scanner.nextLine();

        System.out.println("Numbers of pages:");
        pages = Integer.parseInt(scanner.nextLine());

        System.out.println("width: ");
        width = Integer.parseInt(scanner.nextLine());

        System.out.println("length: ");
        length = Integer.parseInt(scanner.nextLine());

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(authorDatabaseRepository.findOne(authorId));
        book.setCategory(category);
        book.setDescription(description);
        book.setSubject(subject);
        book.setLength(length);
        book.setWidth(width);
        book.setPages(pages);
        book.setPublishingHouse(publishingHouse);
        book.setReleaseYear(releaseYear);

        System.out.println("Book wil be added to database");
        System.out.println("Do you want to proceed(yes/no)?");
        String resp = scanner.nextLine();
        if(resp.equals("yes")){
            System.out.println("Book added successfully");
            bookDatabaseRepository.saveBook(book, sectionId);

        } else{
            System.out.println("Canceling operation...");
        }
    }
    public void findBookById(){
        System.out.println("Please enter id of book you're looking for");
        Scanner scanner = new Scanner(System.in);
        int bookId = Integer.parseInt(scanner.nextLine());
        if(bookDatabaseRepository.findOne(bookId) != null) {
            System.out.println("The book you are searching is :");
            System.out.println(bookDatabaseRepository.findOne(bookId));
        }
        else{
            System.out.println("We have no such book here, sorry");
        }
    }
    public void findBooksByAuthorId(){
        System.out.println("Please enter id of author");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        Author author = authorDatabaseRepository.findOne(id);
        if(author == null){
            while(author == null){
                System.out.println("We don't have this author");
                System.out.println("Please enter another id or q to quit");
                String resp  = scanner.nextLine();
                if(resp.equals("q")){
                    return;
                }
                else{
                    id = Integer.parseInt(scanner.nextLine());
                    author = authorDatabaseRepository.findOne(id);
                }
            }
        }else{
            System.out.println("Author's books");
            bookDatabaseRepository.getAuthorBooks(id).forEach(System.out::println);
        }

    }
    public void findBooksByAuthorName(){
        System.out.println("Please enter name of author");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Set<Book> bookSet = bookDatabaseRepository.getBooksByAuthorName(name.split(" ")[0], name.split(" ")[1]);
        if(bookSet.size() == 0){
            System.out.println("No books found for this author");
        }
        else{
            System.out.println("Books we found: ");
            bookSet.forEach(System.out::println);
        }
    }
    public void findBooksByTitle(){

        System.out.println("Please enter title of book you're looking for");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        Set<Book> bookSet = bookDatabaseRepository.getBooksByTitle(title);
        if (bookSet.size() == 0){
            System.out.println("We have no book with that title");
        }
        else{
            System.out.println("Related books");
            bookSet.forEach(System.out::println);
        }


    }
    public void showBooks(){
        if(bookDatabaseRepository.findAll().isEmpty()){
            System.out.println("We have zero books right now :( ");
        }
        else{
            bookDatabaseRepository.findAll().forEach((k, v)->{
                System.out.println(k + "= " + v);
            });
        }
    }
    public void showSectionBooks(){
        System.out.println("Please enter id of section you want to get books from");
        Scanner scanner = new Scanner(System.in);
        int section_id = Integer.parseInt(scanner.nextLine());
        if(sectionDatabaseRepository.findOne(section_id) == null){
            System.out.println("We have no such section");
        }
        else{
            if(bookDatabaseRepository.getSectionBooks(section_id)!= null){
                System.out.println("Books from this section : ");
                bookDatabaseRepository.getSectionBooks(section_id).forEach(System.out::println);
            }
            else{
                System.out.println("This section has no books yet!");
            }

        }
    }
    public void updateBookData(){

        System.out.println("Please enter id of book to edit");
        Scanner scanner = new Scanner(System.in);
        int bookId = Integer.parseInt(scanner.nextLine());
        Book book = bookDatabaseRepository.findOne(bookId);
        if(book == null){
            while(book == null){
                System.out.println("We don't have this book");
                System.out.println("Please enter id of book to edit or q to quit");
                String resp = scanner.nextLine();
                if(resp.equals("q")){
                    return;
                }
                else{
                    bookId = Integer.parseInt(resp);
                    book = bookDatabaseRepository.findOne(bookId);
                }
            }
        }else{
            System.out.println("Book you want to edit: ");
            System.out.println(book);

            System.out.println("Do you want to edit title(yes/no)?");
            String resp = scanner.nextLine();
            if(resp.equals("yes")){
                System.out.println("Please enter new title");
                String title = scanner.nextLine();
                book.setTitle(title);
            }
            System.out.println("Do you want to proceed(yes/no)?");
            String r = scanner.nextLine();
            if(r.equals("yes")){
                System.out.println("Book modified!");
                bookDatabaseRepository.update(book);
            }
            else{
                System.out.println("Operation canceled...");
            }
        }
    }
    public void removeBook(){
        System.out.println("Please enter id of book you want to remove");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        Book book = bookDatabaseRepository.findOne(id);
        if(book == null){
            System.out.println("We dont have this book");
        }else{
            bookDatabaseRepository.delete(id);
            System.out.println("Book removed!");
        }
    }
    public void showBooksOrderedByTitleAsc(){
        if(bookDatabaseRepository.getBooksOrderedByTitleAsc().size() == 0){
            System.out.println("We have zero books now! :(");
        }
        else{
            System.out.println("Books ordered by title asc");
            bookDatabaseRepository.getBooksOrderedByTitleAsc().forEach(System.out::println);
        }

    }
    public void showBooksOrderedByTitleDesc(){
        if(bookDatabaseRepository.getBooksOrderedByTitleDesc().size() == 0){
            System.out.println("We have zero books now :(");
        }
        else{
            System.out.println("Books ordered by title desc");
            bookDatabaseRepository.getBooksOrderedByTitleDesc().forEach(System.out::println);
        }

    }

}
