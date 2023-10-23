public class Bilet {
    private final Sala sala;
    private final Seans seans;
    private final Siedzenie siedzenie;
    private final String wlasciciel;

    public Bilet(Sala sala, int id, int r, int m, String wlasciciel){
        this.sala = sala;
        seans = this.sala.getSeans(id);
        siedzenie = sala.getMiejsce(r, m);
        this.wlasciciel = wlasciciel;
    }

    public Sala getSala() {
        return sala;
    }

    public Seans getSeans() {
        return seans;
    }

    public Siedzenie getSiedzenie() {
        return siedzenie;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public void wypiszBilet(){
        System.out.println("Właściciel: " + getWlasciciel());
        System.out.println("Tytuł: " + seans.getFilm().getTytul());
        System.out.println("Godzina: " + seans.getStart());
        System.out.println("Miejsce: " + siedzenie.getRzad() + "r " + siedzenie.getMiejsce() + "m");
        System.out.println("Cena biletu: " + seans.getCena());
    }
}
