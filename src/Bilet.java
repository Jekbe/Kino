public class Bilet extends Osoba {
    private final Seans seans;
    private final Siedzenie siedzenie;

    public Bilet(Sala sala, Seans seans, int r, int m, String imie, String nazwisko, int wiek){
        super(imie, nazwisko, wiek);
        this.seans = seans;
        siedzenie = sala.getSiedzenie(r, m);
    }

    public Siedzenie getSiedzenie() {
        return siedzenie;
    }

    @Override
    public String toString(){
        return super.toString() + ": " + seans.toString();
    }
}
