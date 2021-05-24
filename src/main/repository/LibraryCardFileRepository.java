package main.repository;

import main.domain.Audit;
import main.domain.Librarian;
import main.domain.LibraryCard;

import java.time.LocalDate;
import java.util.List;

public class LibraryCardFileRepository extends AbstractFileRepository<Integer, LibraryCard>{

    public LibraryCardFileRepository(Repository<Integer, Audit> auditRepository, String fileName){
        super(auditRepository, fileName);
    }

    @Override
    protected LibraryCard extractEntity(List<String> attributes) {

        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setId(Integer.parseInt(attributes.get(0)));
        libraryCard.setReleaseDate(LocalDate.parse(attributes.get(1)));
        libraryCard.setExpireDate(LocalDate.parse(attributes.get(2)));
        return libraryCard;
    }

    @Override
    protected String createEntityAsString(LibraryCard entity) {

        return entity.getId() + "," + entity.getReleaseDate() + "," + entity.getExpireDate();
    }
}
