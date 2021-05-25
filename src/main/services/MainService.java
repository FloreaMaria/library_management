package main.services;

import main.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

public class MainService {


    private static final ArrayList<Client> clients = new ArrayList<Client>();
    private static final ArrayList<LibraryCard> cards = new ArrayList<LibraryCard>();
    private static final ArrayList<Book> books = new ArrayList<Book>();
    private static final ArrayList<Author> authors = new ArrayList<Author>();
    private static final Set<Librarian> librarians = new HashSet<Librarian>();
    private static final Set<Section> sections =  new HashSet<Section>();
    private static final Library library = new Library();

    public MainService(){

        addAuthor();
        addAuthor();
        addBook();
        addBook();
        addBook();
        createNewClient();
        createNewClient();
        createNewClient();
        addLibrarian();
        addLibrarian();
//        Section s = new Section("SF", "rank 1");
//        Section s1 = new Section("Fantasy", "rank 2");
//        Section s2 = new Section("Horror", "rank 3");
//        Section s3 = new Section("Romance", "rank 4");
//        sections.add(s);
//        sections.add(s1);
//        sections.add(s2);
//        sections.add(s3);
        library.setSectionSet(sections);
    }

    //CLIENT
    public void createNewClient(){
        System.out.println("Creating new client");
        Client newClient = new Client();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstName = null, lastName = null, address = null;

        try {
            System.out.println("First name : ");
            firstName = reader.readLine();
            System.out.println("Last name : ");
            lastName = reader.readLine();
            System.out.println("Address : ");
            address = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        newClient.setFirstName(firstName);
        newClient.setLastName(lastName);
        newClient.setAddress(address);
        LibraryCard libraryCard = new LibraryCard();
        newClient.setLibraryCard(libraryCard);

        cards.add(libraryCard);


        System.out.println("The client " + firstName + " " + lastName + " will have a new LibraryCard and an empty rent record\n");
        System.out.println("Do you want to add this client into database?(yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        if(response.equals("yes")){
            addClient(newClient);
            System.out.println("Client added successfully");
        }
        else
        {
            System.out.println("Client not added!");
        }

    }
    public void addClient(Client c){
        clients.add(c);
    }
    public void showClients(){
        if(clients.size()==0){
            System.out.println("We have 0 clients right now :( ");
        }
        else{
            System.out.println("Our clients: ");
            for (Client client: clients) {
                System.out.println(client);
            }
        }

    }
    public ArrayList<Client> searchClientByFirstName(String firstName){
        ArrayList<Client> foundClients = new ArrayList<Client>();
        for (Client client: clients) {
            if(client.getFirstName().equals(firstName)){
                foundClients.add(client);
            }
        }
        return foundClients;
    }
    public ArrayList<Client> searchClientByLastName(String lastName){
        ArrayList<Client> foundClients = new ArrayList<Client>();
        for (Client client: clients) {
            if(client.getLastName().equals(lastName)){
                foundClients.add(client);
            }
        }
        return foundClients;
    }

    public int getNumberOfPenalizedClients(){
        int nr = 0;
        for(Client c : clients){
            if(c.calculatePenalty() > 0){
                nr += 1;
            }
        }
        return nr;
    }
    public void getPenalizedClients(){
        ArrayList<Client> penalizedClients =  new ArrayList<Client>();
        for(Client client: clients){
            if(client.calculatePenalty() > 0){
                penalizedClients.add(client);
            }
        }
        penalizedClients.sort(Comparator.comparing(Client::getFirstName)
                .thenComparing(Client::getLastName));

        for(Client client: penalizedClients){
            System.out.println(client);
        }
    }


    // BOOK
    public void addBook(){
        System.out.println("Add book");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Title");
        String title = scanner.nextLine();

        System.out.println("Author First name : ");
        String authFn = scanner.nextLine();

        System.out.println("Auhtor Last name : ");
        String authLn = scanner.nextLine();

        System.out.println("Publishin House: ");
        String publishingHouse = scanner.nextLine();

        System.out.println("Category : ");
        String category = scanner.nextLine();

        System.out.println("Description");
        String descr = scanner.nextLine();

        System.out.println("Subject");
        String subject = scanner.nextLine();

        System.out.println("Price, Number of pages, Release Year, Width, Length");
        String[] frag = scanner.nextLine().split(",");

        double price = Double.parseDouble(frag[0]);
        int numberOfPages = Integer.parseInt(frag[1]);
        int releaseYear = Integer.parseInt(frag[2]);
        int width = Integer.parseInt(frag[3]);
        int length = Integer.parseInt(frag[4]);

        for(Author auth: authors){
            if(auth.getLastName().equals(authLn) && auth.getFirstName().equals(authFn)) {
                Book newBook = new Book(
                        numberOfPages,
                        length,
                        width,
                        releaseYear,
                        price,
                        title,
                        publishingHouse,
                        category,
                        descr,
                        subject,
                        auth);
                auth.getBookArray().add(newBook);
                books.add(newBook);
            }
            else{

                Author author = new Author();
                author.setLastName(authLn);
                author.setFirstName(authFn);
                Set<Book> auth_books = new HashSet<Book>();

                Book newBook = new Book(
                        numberOfPages,
                        length,
                        width,
                        releaseYear,
                        price,
                        title,
                        publishingHouse,
                        category,
                        descr,
                        subject,
                        author);

                auth_books.add(newBook);
                author.setBookArray(auth_books);
                books.add(newBook);

                }
        }
        System.out.println("Book added successfully!");
    }
    public ArrayList<Book> searchBooksByTitle(String title){
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book: books) {
            if(book.getTitle().equals(title)){
                foundBooks.add(book);
            }
        }
      return foundBooks;
    }
    public ArrayList<Book> searchBooksByAuthorName(String name){
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        String[] names = name.split("\\s");
        for (Book book: books) {
                for(String n: names){
                    if(book.getAuthor().getFirstName().equals(n) || book.getAuthor().getLastName().equals(n)){
                        foundBooks.add(book);
                    }
                }
        }
        return foundBooks;
    }
    public void orderBooksByTitle() {
        books.sort(Book.bookTitleComparator);
        for(Book book: books){
            System.out.println(book);
        }
    }
    public void orderBookByPrice(){
        books.sort(Comparator.comparingDouble(Book::getPrice));
        for(Book book: books){
            System.out.println(book);
        }
    }
//    public int getNumberOfCopies(Book b){
//        int nr = 0;
//        for(BookItem bookItem: bookItems){
//            if(bookItem.getBookId() == b.getBookId()){
//                nr +=1;
//            }
//        }
//        return nr;
//    }
    //AUTHOR
    public void addAuthor(){
    System.out.println("Add author");
    int ok =0;
    Scanner scanner = new Scanner(System.in);
    System.out.println("FirstName: ");
    String firstName = scanner.nextLine();
    System.out.println("LastName: ");
    String lastName = scanner.nextLine();

    for(Author auth: authors){
        if (auth.getLastName().equals(lastName) && auth.getFirstName().equals(firstName)) {
            ok = 1;
            break;
        }
    }
    if(ok > 0){
        System.out.println("Author already exists!");
    }
    else{
        Set<Book> authBooks = new HashSet<Book>();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBookArray(authBooks);
        authors.add(author);
        System.out.println("Author was added!");
    }
}
    public void displayAuthors(){
        for(Author author: authors){
            System.out.println(author);
        }
    }
    public void searchBooksOfAuthor(){

        System.out.println("Enter author first name, lastname");
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split(",");
        int ok = 0;
        for(Author auth: authors){
            if(auth.getLastName().equals(names[1]) && auth.getFirstName().equals(names[0])){
                for(Book book: books){
                    if(book.getAuthor() == auth){
                        System.out.println(book);
                        ok = 1;
                    }
                }
            }
        }
        if(ok == 0){
            System.out.println("This author has no books");
        }

    }

    //LIBRARIAN
    public void displayLibrarians(){
        for(Librarian librarian : librarians){
            System.out.println(librarian);
        }
    }
    public void addLibrarian(){
        System.out.println("Enter first name, last name for new librarian to be hired");
        Scanner scanner = new Scanner(System.in);
        String[] doc = scanner.nextLine().split(" ");

        Librarian librarian = new Librarian(doc[0], doc[1]);
        librarians.add(librarian);
        System.out.println("Librarian hired successfully");
    }

    public double collectPenaltyFines(){
        double amount = 0;
        for(Client client : clients){
            amount += client.calculatePenalty();
        }
        return amount;
    }











}
