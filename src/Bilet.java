public class Bilet {
    private final Seans seans;
    private final Siedzenie siedzenie;
    private final String wlasciciel;

    public Bilet(Sala sala, Seans seans, int r, int m, String wlasciciel){
        this.seans = seans;
        siedzenie = sala.getSiedzenie(r, m);
        this.wlasciciel = wlasciciel;
    }

    public Siedzenie getSiedzenie() {
        return siedzenie;
    }

    @Override
    public String toString(){
        return wlasciciel + ": " + seans.toString();
    }
}
