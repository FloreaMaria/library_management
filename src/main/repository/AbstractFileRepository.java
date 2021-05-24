package main.repository;


import main.domain.*;

import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID,E> {

    String fileName;
    protected Repository<Integer, LibraryCard> libraryCardRepository;
    protected Repository<Integer, Rent> rentRepository;
    private String header;
    protected Repository<Integer, Audit> auditRepository;
    protected Repository<Integer, BookItem> bookItemRepository;
    protected Repository<Integer, Librarian> librarianRepository;
    protected Repository<Integer, Book> bookRepository;


    public AbstractFileRepository(String fileName) {
        this.fileName = fileName;
        loadData();
    }

    public AbstractFileRepository(Repository<Integer, Librarian> librarianRepository, String fileName, Repository<Integer, Audit> auditRepository) {
        this.fileName = fileName;
        this.librarianRepository = librarianRepository;
        this.auditRepository = auditRepository;
        loadData();
    }


    public AbstractFileRepository(String fileName, Repository<Integer, Audit> auditRepository, Repository<Integer, BookItem> bookItemRepository) {
        this.fileName = fileName;
        this.bookItemRepository = bookItemRepository;
        this.auditRepository = auditRepository;
        loadData();
    }


    public AbstractFileRepository(Repository<Integer, Audit> auditRepository, String fileName, Repository<Integer, LibraryCard> libraryCardRepository,
                                  Repository<Integer, Rent> rentRepository) {
        this.fileName = fileName;
        this.libraryCardRepository = libraryCardRepository;
        this.rentRepository = rentRepository;
        this.auditRepository = auditRepository;
        loadData();
    }
    public AbstractFileRepository(String fileName, Repository<Integer, Librarian> librarianRepository, Repository<Integer, Audit> auditRepository,
                                 Repository<Integer, Book> bookRepository){
        this.fileName = fileName;
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
        this.auditRepository = auditRepository;
        loadData();
    }

    public AbstractFileRepository(Repository<Integer, Audit> auditRepository, String fileName) {
        this.auditRepository = auditRepository;
        this.fileName = fileName;
        loadData();
    }
    public AbstractFileRepository(Repository<Integer, Book> bookRepository,  Repository<Integer, Audit> auditRepository, String fileName) {
        this.auditRepository = auditRepository;
        this.fileName = fileName;
        this.bookRepository = bookRepository;
        loadData();
    }


    private void loadData() {
        final Boolean[] isHeader = {true};
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(line -> {
                if(isHeader[0]){
                    isHeader[0] = false;
                    header = line;
                }
                else{
                    List<String> attributes = Arrays.asList(line.split(",")); // Split the attributes by ","
                    E e = extractEntity(attributes); // Create the Entity based on the attributes
                    super.save(e); // Add the loaded Entity in the Repository
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void reload() {
        Iterable<E> currentEntities = super.findAll();
        currentEntities.forEach(System.out::println);
        try {
            PrintWriter writer =  new PrintWriter(fileName);
            writer.print("");
            writer.println(header);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File to reload doesn't exist!");
        }
        currentEntities.forEach(this::writeToFile);
    }


    protected abstract E extractEntity(List<String> attributes);
    protected abstract String createEntityAsString(E entity);

    @Override
    public E save(E entity){
        E e = super.save(entity);
        if (e==null) {
            writeToFile(entity);
        }
        return e;
    }

    @Override
    public E delete(ID id) {
        E e = super.delete(id);
        if (e != null) {
            this.reload();
        }
        return e;
    }

    @Override
    public E update(E entity) {
        E e = super.update(entity);
//        if (e == null) {
//            writeToFile(entity);
//        }
        return e;
    }

    protected void writeToFile(E entity){

        String action = "";
        Date date = new Date();
        String timeStamp = date.toString();

        if(entity instanceof Client){
            action = "Writing object of type CLIENT";
            Audit audit = new Audit(action,timeStamp);
            auditRepository.save(audit);
        }
        else if (entity instanceof Rent){
            action = "Writing object of type RENT";
            Audit audit = new Audit(action,timeStamp);
            auditRepository.save(audit);
        }
        else if(entity instanceof Librarian){
            action = "Writing object of type LIBRARIAN";
            Audit audit = new Audit(action,timeStamp);
            auditRepository.save(audit);
        }
        else if(entity instanceof Section){
            action = "Writing object of type SECTION";
            Audit audit = new Audit(action,timeStamp);
            auditRepository.save(audit);
        }
        else if(entity instanceof LibraryCard){
            action = "Writing object of type LIBRARYCARD";
            Audit audit = new Audit(action,timeStamp);
            auditRepository.save(audit);
        }

        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName,true))) {
            bW.write(createEntityAsString(entity));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

