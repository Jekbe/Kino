public class Film {
    private final String tytul, opis;
    private final int czas;
    private final int wiek;

    public Film(String tytul, String opis, int czas, int wiek) {
        this.tytul = tytul;
        this.opis = opis;
        this.czas = czas;
        this.wiek = wiek;
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

    public int getWiek() {
        return wiek;
    }

    public boolean tylkoDlaDoroslych(){
        return wiek >= 18;
    }
}
