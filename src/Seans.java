import java.time.LocalDateTime;

public class Seans{
    private final LocalDateTime start;
    private final int cena;
    private final Film film;

    public Seans(Film film, LocalDateTime start, int cena) {
        this.film = film;
        this.start = start;
        this.cena = cena;
    }

    public LocalDateTime getStart(){
        return start;
    }

    public int getCena() {
        return cena;
    }

    public Film getFilm() {
        return film;
    }
}
