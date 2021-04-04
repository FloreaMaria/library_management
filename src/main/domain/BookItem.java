package main.domain;

public class BookItem extends Book{

    private int bookItemId;
    private String status; // available, borrowed

    public BookItem(int bookItemId, String status) {
        this.bookItemId = bookItemId;
        this.status = status;
    }

    public BookItem(int bookId, int pages, int length, int width, int releaseYear, double price, String title, String publishingHouse, String category, String description, String subject, Author author, int bookItemId, String status) {
        super(bookId, pages, length, width, releaseYear, price, title, publishingHouse, category, description, subject, author);
        this.bookItemId = bookItemId;
        this.status = status;
    }

    public int getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(int bookItemId) {
        this.bookItemId = bookItemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
