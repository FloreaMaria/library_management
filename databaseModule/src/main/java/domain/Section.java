package domain;

import java.util.Set;

public class Section extends Entity<Integer> {

    private String name, location;
    private static int sectionId = 0;
    private Set<Librarian> librarianSet;
    private Set<Book> bookSet;

    public Section(){
        sectionId++;
        this.setId(sectionId);
    }
    public Section(String name, String location, Set<Book> bookSet, Set<Librarian> librarianSet) {
        sectionId++;
        this.setId(sectionId);
        this.name = name;
        this.location = location;
        this.bookSet = bookSet;
        this.librarianSet = librarianSet;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Librarian> getLibrarianSet() {
        return librarianSet;
    }

    public void setLibrarianSet(Set<Librarian> librarianSet) {
        this.librarianSet = librarianSet;
    }


    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    @Override
    public String toString() {
        return "Section{" +
                " name='" + name + '\'' +
                ", location='" + location + '\'' +
                " librarianSet = " + librarianSet +
                " bookSet = " + bookSet +
                '}';
    }
}
