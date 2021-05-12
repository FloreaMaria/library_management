package main.domain;

import java.util.Arrays;
import java.util.Set;

public class Section extends Entity<Integer>{

    private String name, location;
    private int sectionId;

    Set<Librarian> librarianSet;


    public Section(String name, String location) {
        this.name = name;
        this.location = location;
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

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public Set<Librarian> getLibrarianSet() {
        return librarianSet;
    }

    public void setLibrarianSet(Set<Librarian> librarianSet) {
        this.librarianSet = librarianSet;
    }

    @Override
    public String toString() {
        return "Section{" +
                " name='" + name + '\'' +
                ", location='" + location + '\'' +
                "librarianSet = " + librarianSet +
                '}';
    }
}
