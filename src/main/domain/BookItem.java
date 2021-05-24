package main.domain;

public class BookItem extends Book{


    private String status; // available, borrowed
    private static int bookItemId = 0;

    public BookItem(){
        bookItemId++;
        this.setId(bookItemId);
    }
    public BookItem( String status) {
        this.status = status;
        bookItemId++;
        this.setId(bookItemId);
    }

    public BookItem( int pages, int length, int width, int releaseYear, double price, String title, String publishingHouse, String category, String description, String subject, Author author,String status) {
        super(pages, length, width, releaseYear, price, title, publishingHouse, category, description, subject, author);
        this.status = status;
        bookItemId++;
        this.setId(bookItemId);
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
