package domain;

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

    public BookItem(int pages, int length, int width, int releaseYear, String title,
                    String publishingHouse, String category, String description, String subject, Author author, String status) {
        super(pages, length, width, releaseYear, title, publishingHouse, category, description, subject, author);
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
    @Override
    public String toString() {
        return "BookItem{" +
                " bookId=" + getId() +
                ", status=" +getStatus() +
                ", pages=" + getPages() +
                ", length=" + getLength() +
                ", width=" + getWidth() +
                ", releaseYear=" + getReleaseYear() +
                ", title='" + getTitle() + '\'' +
                ", publishingHouse='" + getPublishingHouse() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", subject='" + getSubject() + '\'' +
                ", author=" + getAuthor().getFirstName() +

                '}';
    }
}
