public class Siedzenie {
    private boolean wolne;
    private final int rzad;
    private final int miejsce;

    public Siedzenie(int rzad, int mmiejsce){
        wolne = true;
        this.rzad = rzad;
        this.miejsce = mmiejsce;
    }

    public boolean getWolne(){
        return wolne;
    }

    public int getRzad() {
        return rzad;
    }

    public int getMiejsce() {
        return miejsce;
    }

    public void zajmij(){
        wolne = false;
    }

    public void zwolnij(){
        wolne = true;
    }
}
