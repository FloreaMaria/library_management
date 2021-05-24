package main.repository;

import main.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientFileRepository extends AbstractFileRepository<Integer, Client>{

    public ClientFileRepository(String fileName, Repository<Integer, LibraryCard> libraryCardRepository,
                                Repository<Integer, Rent> rentFileRepository, Repository<Integer, Audit> auditRepository){
        super(auditRepository, fileName, libraryCardRepository, rentFileRepository);

    }

    @Override
    public Client extractEntity(List<String> attributes) {

        Integer idClient = Integer.parseInt(attributes.get(0));
        String firstName = attributes.get(1);
        String lastName = attributes.get(2);
        String address = attributes.get(3);
        Integer idCard = Integer.parseInt(attributes.get(4));
        LibraryCard libraryCard = libraryCardRepository.findOne(idCard);

        Rent[] rents = new Rent[attributes.get(5).split(";").length];
        AtomicInteger top = new AtomicInteger();

        Arrays.stream(attributes.get(5).split(";")).forEach((idRentString) ->{
            int idRentInt = Integer.parseInt(idRentString);
            Rent rent = rentRepository.findOne(idRentInt);
            rents[top.getAndIncrement()] = rent;
        });

        Client client =  new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAddress(address);
        client.setLibraryCard(libraryCard);
        client.setRentedBooks(rents);
        return client;

    }

    @Override
    protected String createEntityAsString(Client entity) {

        StringBuilder rentedBooksIds = new StringBuilder();
        for (Rent b : entity.getRentedBooks()) {
            rentedBooksIds.append(b.getId()).append(";");
        }
        rentedBooksIds.deleteCharAt(rentedBooksIds.length()-1);
        return entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + entity.getAddress()
                + "," + entity.getLibraryCard().getId() + "," + rentedBooksIds;

    }
}
