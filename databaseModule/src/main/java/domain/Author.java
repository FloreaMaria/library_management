package domain;

import java.util.HashSet;
import java.util.Set;

public class Author extends Person {

    private Set<Book> bookArray = new HashSet<>();
    private static int authorId = 0;

    public  Author(){
        authorId ++;
        this.setId(authorId);
    }

    public Author(Set<Book> bookArray) {
        this.bookArray = bookArray;
    }

    public Author(String firstName, String lastName, Set<Book> bookArray) {
        super(firstName, lastName);
        this.bookArray = bookArray;
    }

    public Set<Book> getBookArray() {
        return bookArray;
    }

    public void setBookArray(Set<Book> bookArray) {
        this.bookArray = bookArray;
    }

    public void addBook(Book book){
        bookArray.add(book);
    }
    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + getFirstName()+ '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", books = " + getBookArray() +
                '}';
    }
}