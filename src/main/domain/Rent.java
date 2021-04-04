package main.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Rent {

    private int rentId;
    private double penalty;
    private BookItem bookItem;
    private LocalDate rentDate, actualReturnDate;

    public Rent(){

        this.rentDate = LocalDate.now();
        this.penalty = 0;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public int getRentId() {
        return rentId;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }


    public BookItem getBook() {
        return bookItem;
    }

    public void setBook(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public LocalDate getReturnDate(){
        return rentDate.plusDays(20);
    }

    public void calculatePenalty(){
        LocalDate currentDate = LocalDate.now();
        if(this.actualReturnDate == null && this.getReturnDate().isBefore(currentDate))
            this.penalty = ChronoUnit.DAYS.between(this.getRentDate(), currentDate) * (0.01 * bookItem.getPrice());
    }

    @Override
    public String toString() {
        return "Rent{" +
                "rentId=" + rentId +
                ", penalty=" + penalty +
                ", book=" + bookItem +
                ", rentDate=" + rentDate +
                ", expectedReturnDate=" + getReturnDate() +
                ", actualReturnDate=" + actualReturnDate +
                '}';
    }
}
