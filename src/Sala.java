import java.util.ArrayList;
import java.util.List;

public class Sala {
    private final int numer, rzedy, miejsca;
    private final Siedzenie[][] siedzenia;
    private final List<Seans> seanse = new ArrayList<>();

    public Sala(int numer, int rzedy, int miejsca){
        this.numer = numer;
        this.rzedy = rzedy;
        this.miejsca = miejsca;
        this.siedzenia = new Siedzenie[rzedy][miejsca];
        for (int f1 = 0; f1 < rzedy; f1++){
            for (int f2 = 0; f2 < miejsca; f2++){
                this.siedzenia[f1][f2] = new Siedzenie();
            }
        }
    }

    public int getNumer(){
        return numer;
    }

    public Siedzenie getSiedzenie(int r, int m){
        return this.siedzenia[r][m];
    }

    public int getRzedy() {
        return rzedy;
    }

    public int getMiejsca() {
        return miejsca;
    }

    public boolean dodajSeans(Seans nowySeans){
        for (Seans seans : seanse) {
            if (seans.start().equals(nowySeans.start())) {
                return false;
            }
        }

        seanse.add(nowySeans);
        return true;
    }
}
