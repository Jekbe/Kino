import java.util.ArrayList;
import java.util.List;

public class Sala {
    private final int numer;
    private final Siedzenie[][] miejsca;
    private final List<Seans> seanse = new ArrayList<>();

    public Sala(int numer, int rzedy, int miejsca){
        this.numer = numer;
        this.miejsca = new Siedzenie[rzedy][miejsca];
        for (int f1 = 0; f1 < rzedy; f1++){
            for (int f2 = 0; f2 < miejsca; f2++){
                this.miejsca[f1][f2] = new Siedzenie(f1, f2);
            }
        }
    }

    public int getNumer(){
        return numer;
    }

    public Seans getSeans(int id){
        return seanse.get(id - 1);
    }

    public Siedzenie getMiejsce(int r, int m){
        return this.miejsca[r][m];
    }

    public boolean dodajSeans(Seans nowySeans){
        for (Seans seans : seanse) {
            if (seans.getStart().equals(nowySeans.getStart())) {
                return false;
            }
        }

        seanse.add(nowySeans);
        return true;
    }

    public void pokazSeanse(){
        for (int f1 = 0; f1 < seanse.size(); f1++) {
            Seans seans = seanse.get(f1);
            System.out.println("ID: " + (f1 + 1));
            System.out.println("Tytuł: " + seans.getFilm().getTytul());
            System.out.println("Czas trwania: " + seans.getFilm().getTytul());
            System.out.println("Godzina rozpoczęcia: " + seans.getStart());
        }
    }
}
