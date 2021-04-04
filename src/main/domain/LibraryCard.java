package main.domain;

import java.time.LocalDate;

public class LibraryCard {

    private int cardId;
    private LocalDate releaseDate, expireDate;

    public LibraryCard(){
    }

    public LibraryCard(int cardId, LocalDate releaseDate, LocalDate expireDate) {
        this.cardId = cardId;
        this.releaseDate = releaseDate;
        this.expireDate = expireDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    boolean isExpired(){
        return (expireDate.isBefore(LocalDate.now()));
    }


    @Override
    public String toString() {
        return "LibraryCard{" +
                "cardId=" + cardId +
                ", releaseDate=" + releaseDate +
                ", expireDate=" + expireDate +
                '}';
    }
}
