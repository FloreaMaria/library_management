package main.domain;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

public class Librarian extends Person{

    private static int librarianId = 0;
    private LocalDate hireDate;

    public Librarian(){
        librarianId++;
        this.setId(librarianId);
    }

    public Librarian(String firstName, String lastName) {
        super(firstName, lastName);
        librarianId++;
        this.setId(librarianId);

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
                ", librarianId=" + getId() +
                '}';
    }
}
