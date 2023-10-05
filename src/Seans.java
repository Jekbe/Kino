import java.time.LocalDateTime;

public class Seans extends Film{
    private final LocalDateTime start;

    public Seans(String tytul, String opis, int czas, int rok, int miesac, int dzien, int godz, int min, Sala sala) {
        super(tytul, opis, czas);
        start = LocalDateTime.of(rok, miesac, dzien, godz, min);
    }

    public LocalDateTime getStart(){
        return start;
    }
}
