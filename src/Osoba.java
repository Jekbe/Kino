public abstract class Osoba {
    private final String imie, nazwisko;
    private final int wiek;

    public Osoba(String imie, String nazwisko, int wiek) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    public int getWiek() {
        return wiek;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }
}
