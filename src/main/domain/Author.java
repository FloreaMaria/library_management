package main.domain;
import java.util.Arrays;
import java.util.Set;

public class Author extends Person{

    private Set<Book> bookArray;
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
}