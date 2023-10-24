public record Film(String tytul, String opis, int czas, int wiek) {

    public boolean tylkoDlaDoroslych() {
        return wiek >= 18;
    }

    @Override
    public String toString() {
        return tytul;
    }
}
