import java.time.LocalDateTime;

public record Seans(Film film, LocalDateTime start, int cena, Sala sala) {

    @Override
    public String toString() {
        return film.tytul() + ": " + start;
    }
}
