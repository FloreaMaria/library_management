package main.domain;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

public class Librarian extends Person{

    private int librarianId;
    LocalDate hireDate;

    public Librarian(){
    }

    public Librarian(int librarianId) {
        this.librarianId = librarianId;

    }

    public Librarian(String firstName, String lastName, int librarianId) {
        super(firstName, lastName);
        this.librarianId = librarianId;

    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "First name = " + getFirstName() +
                ", Last name = " + getLastName() +
                ", librarianId=" + librarianId +
                '}';
    }
}
