public class Siedzenie {
    private boolean wolne;

    public Siedzenie(){
        wolne = true;
    }

    public boolean getWolne(){
        return wolne;
    }

    public void zajmij(){
        wolne = false;
    }

    public void zwolnij(){
        wolne = true;
    }
}
