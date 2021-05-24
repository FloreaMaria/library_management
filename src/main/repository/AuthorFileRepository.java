package main.repository;

import main.domain.Audit;
import main.domain.Author;
import main.domain.Book;
import main.domain.Librarian;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorFileRepository extends AbstractFileRepository<Integer, Author>{


    public AuthorFileRepository(String fileName, Repository<Integer, Audit> auditRepository, Repository<Integer, Book> bookRepository) {
        super(bookRepository, auditRepository, fileName);
    }

    @Override
    protected Author extractEntity(List<String> attributes) {
        Author author = new Author();
        author.setId(Integer.parseInt(attributes.get(0)));
        author.setFirstName(attributes.get(1));
        author.setLastName(attributes.get(2));

        Set<Book> bookSet = new HashSet<>();
        Arrays.stream(attributes.get(3).split(";")).forEach((idBook)->{
            int idBookInt = Integer.parseInt(idBook);
            Book book  = bookRepository.findOne(idBookInt);
            bookSet.add(book);
        });
        author.setBookArray(bookSet);
        return author;
    }

    @Override
    protected String createEntityAsString(Author entity) {

        StringBuilder booksIds = new StringBuilder();
        for (Book b : entity.getBookArray()) {
            booksIds.append(b.getId()).append(";");
        }
        booksIds.deleteCharAt(booksIds.length()-1);
        return entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + booksIds;
    }
}
