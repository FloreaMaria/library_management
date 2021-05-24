package domain;

import java.time.LocalDate;

public class Librarian extends Person {

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
                " librarianId = " + getId() +
                ", FirstName = " + getFirstName() +
                ", LastName = " + getLastName() +
                '}';
    }
}
