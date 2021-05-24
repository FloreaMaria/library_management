package domain;

import java.util.Set;

public class Library extends Entity<Integer> {

    private static int libraryId = 0;
    private String libraryName, libraryAddress;
//    private List<BookItem> books;
//    private Set<Librarian> librarianSet;
    private Set<Section> sectionSet;

    public Library(){
        libraryId++;
        this.setId(libraryId);

    }

    public Library(String libraryName, String libraryAddress, Set<Section> sectionSet) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
//        this.books = books;
//        this.librarianSet = librarianSet;
        this.sectionSet = sectionSet;
        libraryId++;
        this.setId(libraryId);
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

//    public List<BookItem> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<BookItem> books) {
//        this.books = books;
//    }

//    public Set<Librarian> getLibrarianSet() {
//        return librarianSet;
//    }
//
//    public void setLibrarianSet(Set<Librarian> librarianSet) {
//        this.librarianSet = librarianSet;
//    }

    public Set<Section> getSectionSet() {
        return sectionSet;
    }

    public void setSectionSet(Set<Section> sectionSet) {
        this.sectionSet = sectionSet;
    }

}
