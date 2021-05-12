package main.repository;

import main.domain.Audit;
import main.domain.Book;
import main.domain.BookItem;
import main.domain.Rent;

import java.time.LocalDate;
import java.util.List;

public class RentFileRepository extends AbstractFileRepository<Integer, Rent>{

    public RentFileRepository(String filePath, Repository<Integer, BookItem> bookItemRepository, Repository<Integer, Audit> auditRepository){
        super(filePath, auditRepository, bookItemRepository);
    }

    @Override
    protected Rent extractEntity(List<String> attributes) {

        Rent rent = new Rent();
        rent.setId(Integer.parseInt(attributes.get(0)));
        rent.setPenalty(Double.parseDouble(attributes.get(1)));
        rent.setRentDate(LocalDate.parse(attributes.get(2)));
        rent.setActualReturnDate(LocalDate.parse(attributes.get(3)));
        BookItem bookItem = bookItemRepository.findOne(Integer.parseInt(attributes.get(4)));
        rent.setBook(bookItem);
        return rent;

    }

    @Override
    protected String createEntityAsString(Rent entity) {
        return entity.getId() + "," + entity.getPenalty() + "," +
                entity.getRentDate() + "," + entity.getActualReturnDate() + "," +
                entity.getBook().getBookId();
    }
}
