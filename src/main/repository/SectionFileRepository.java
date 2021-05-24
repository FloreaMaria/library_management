package main.repository;

import main.domain.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SectionFileRepository extends AbstractFileRepository<Integer, Section>{

    public SectionFileRepository(String fileName, Repository<Integer, Librarian> librarianRepository, Repository<Integer, Audit> auditRepository,
                                 Repository<Integer, Book> bookRepository){
        super(fileName, librarianRepository, auditRepository, bookRepository);
    }

    @Override
    protected Section extractEntity(List<String> attributes) {
        Integer idSection = Integer.parseInt(attributes.get(0));
        String name = attributes.get(1);
        String location = attributes.get(2);
        Set<Librarian> librarianSet = new HashSet<>();
        Set<Book> bookSet = new HashSet<>();

        Arrays.stream(attributes.get(3).split(";")).forEach((idLibrarian) ->{
            int idLibrarianInt = Integer.parseInt(idLibrarian);
            Librarian librarian = librarianRepository.findOne(idLibrarianInt);
            librarianSet.add(librarian);

        });
        Arrays.stream(attributes.get(4).split(";")).forEach((idBook)->{
            int idBookInt = Integer.parseInt(idBook);
            Book book  = bookRepository.findOne(idBookInt);
            bookSet.add(book);
        });

        Section section =  new Section(name, location, bookSet, librarianSet);
        section.setId(idSection);
        return section;
    }

    @Override
    protected String createEntityAsString(Section entity) {
        StringBuilder librarianIds = new StringBuilder();


        for (Librarian l : entity.getLibrarianSet()) {
            librarianIds.append(l.getId()).append(";");
        }
        librarianIds.deleteCharAt(librarianIds.length()-1);

        StringBuilder booksIds = new StringBuilder();
        for (Book b : entity.getBookSet()) {
            booksIds.append(b.getId()).append(";");
        }
        booksIds.deleteCharAt(booksIds.length()-1);
        return entity.getId() + "," + entity.getName() + "," + entity.getLocation() + "," + librarianIds + "," + booksIds;
    }
}
