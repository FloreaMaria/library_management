package main.repository;

import main.domain.Audit;
import main.domain.Entity;
import main.domain.Librarian;

import java.time.LocalDate;
import java.util.List;

public class LibrarianFileRepository extends AbstractFileRepository<Integer, Librarian>
{

    public LibrarianFileRepository(String fileName, Repository<Integer, Audit> auditRepository){
        super(auditRepository, fileName);
    }

    @Override
    protected Librarian extractEntity(List<String> attributes) {
        Librarian librarian = new Librarian();
        librarian.setId(Integer.parseInt(attributes.get(0)));
        librarian.setFirstName(attributes.get(1));
        librarian.setLastName(attributes.get(2));
        librarian.setHireDate(LocalDate.parse(attributes.get(3)));
        return librarian;

    }

    @Override
    protected String createEntityAsString(Librarian entity) {
        return entity.getId() + "," + entity.getFirstName()+ "," + entity.getLastName()+
                ","+ entity.getHireDate();
    }

}
