package main.domain;
import java.util.Arrays;
import java.util.Set;

public class Author extends Person{

    private int authorId;
    private Set<Book> bookArray;
    public  Author(){

    }

    public Author(int authorId, Set<Book> bookArray) {
        this.authorId = authorId;
        this.bookArray = bookArray;
    }

    public Author(String firstName, String lastName, int authorId, Set<Book> bookArray) {
        super(firstName, lastName);
        this.authorId = authorId;
        this.bookArray = bookArray;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Set<Book> getBookArray() {
        return bookArray;
    }

    public void setBookArray(Set<Book> bookArray) {
        this.bookArray = bookArray;
    }
}