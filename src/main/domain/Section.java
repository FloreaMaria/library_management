package main.domain;

import java.util.Arrays;

public class Section {

    private String name, location;

    public Section(){

    }
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


    @Override
    public String toString() {
        return "Section{" +
                " name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
