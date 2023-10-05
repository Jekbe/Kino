import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Okno okno = new Okno();
        okno.setTitle("Kino");
        okno.setSize(800, 600);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);
    }
}