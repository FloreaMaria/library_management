package main.domain;

import java.util.List;
import java.util.Set;

public class Library extends Entity<Integer> {

    private int libraryId;
    private String libraryName, libraryAddress;
    private List<BookItem> books;
    private Set<Librarian> librarianSet;
    private Set<Section> sectionSet;

    public Library(){

    }

    public Library(String libraryName, String libraryAddress, List<BookItem> books, Set<Librarian> librarianSet, Set<Section> sectionSet) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
        this.books = books;
        this.librarianSet = librarianSet;
        this.sectionSet = sectionSet;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    public List<BookItem> getBooks() {
        return books;
    }

    public void setBooks(List<BookItem> books) {
        this.books = books;
    }

    public Set<Librarian> getLibrarianSet() {
        return librarianSet;
    }

    public void setLibrarianSet(Set<Librarian> librarianSet) {
        this.librarianSet = librarianSet;
    }

    public Set<Section> getSectionSet() {
        return sectionSet;
    }

    public void setSectionSet(Set<Section> sectionSet) {
        this.sectionSet = sectionSet;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }
}
