package main.repository;

import main.domain.LibraryCard;

import java.util.List;

public class LibraryCardFileRepository extends AbstractFileRepository<Integer, LibraryCard>{

    public LibraryCardFileRepository(String fileName){
        super(fileName);
    }

    @Override
    protected LibraryCard extractEntity(List<String> attributes) {
        return null;
    }

    @Override
    protected String createEntityAsString(LibraryCard entity) {
        return null;
    }
}
