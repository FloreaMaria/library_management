package domain;

import java.time.LocalDate;

public class LibraryCard extends Entity<Integer> {

    private static int cardId = 0;
    private LocalDate releaseDate, expireDate;

    public LibraryCard(){
        cardId++;
        this.setId(cardId);
        this.releaseDate = LocalDate.now();
        this.expireDate = LocalDate.now().plusYears(2);
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
                "cardId=" + getId() +
                ", releaseDate=" + releaseDate +
                ", expireDate=" + expireDate +
                '}';
    }
}
