public class Film {
    private final String tytul, opis;
    private final int czas;

    public Film(String tytul, String opis, int czas) {
        this.tytul = tytul;
        this.opis = opis;
        this.czas = czas;
    }

    public String getTytul() {
        return tytul;
    }

    public String getOpis() {
        return opis;
    }

    public int getCzas() {
        return czas;
    }
}
