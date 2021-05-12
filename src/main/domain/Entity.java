package main.domain;

public class Entity <E> {

    private E id;

    public E getId() {
        return id;
    }

    public void setId(E id) {
        this.id = id;
    }
}
