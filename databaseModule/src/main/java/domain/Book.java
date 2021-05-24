package domain;

import java.util.Comparator;

public class Book extends Entity<Integer> {

    private static int bookId = 0;
    private int pages, length, width, releaseYear;
    private double price;
    private String title, publishingHouse, category, description, subject;
    private Author author;

    public Book() {
        bookId++;
        this.setId(bookId);
    }

    public Book(int pages, int length, int width, int releaseYear, double price, String title, String publishingHouse,
                String category, String description, String subject, Author author) {
        bookId++;
        this.setId(bookId);
        this.pages = pages;
        this.length = length;
        this.width = width;
        this.releaseYear = releaseYear;
        this.price = price;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.category = category;
        this.description = description;
        this.subject = subject;
        this.author = author;

    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }



    public static Comparator<Book> bookTitleComparator = new Comparator<Book>() {

        public int compare(Book b1, Book b2) {
            String title1 = b1.getTitle().toUpperCase();
            String title2 = b2.getTitle().toUpperCase();

            //ascending order
            return title1.compareTo(title2);

        }};


    public double compare(Book a, Book b)
    {
        return a.price - b.price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + getId() +
                ", pages=" + pages +
                ", length=" + length +
                ", width=" + width +
                ", releaseYear=" + releaseYear +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", author=" + author.getFirstName() +
                '}';
    }


}