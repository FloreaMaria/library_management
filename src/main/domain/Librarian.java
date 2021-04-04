package main.domain;

import java.util.Arrays;
import java.util.Set;

public class Librarian extends Person{

    private int librarianId;
    private Set<Section> sections;

    public Librarian(){

    }

    public Librarian(int librarianId, Set<Section> sections) {
        this.librarianId = librarianId;
        this.sections = sections;
    }

    public Librarian(String firstName, String lastName, int librarianId, Set<Section> sections) {
        super(firstName, lastName);
        this.librarianId = librarianId;
        this.sections = sections;
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "First name = " + getFirstName() +
                ", Last name = " + getLastName() +
                ", librarianId=" + librarianId +
                ", sections=" + sections +
                '}';
    }
}
