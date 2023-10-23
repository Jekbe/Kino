import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class Okno extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel pUser, pAdmin, pButton, pCard, pAdminb;
    private final JButton bAdmin, bUser, bDFilm, bDSeans, bDSala, bSeans;
    private final List<Sala> sale = new ArrayList<>();
    private final List<Film> filmy = new ArrayList<>();
    private final List<Seans> seanse = new ArrayList<>();
    private final List<Bilet> bilety = new ArrayList<>();
    JComboBox<Seans> cbSeanse = new JComboBox<>(seanse.toArray(new Seans[0]));
    JComboBox<Bilet> cbBilety = new JComboBox<>(bilety.toArray(new Bilet[0]));

    public Okno(){
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();

        pUser = new JPanel(new FlowLayout());
        pUser.setLayout(new BoxLayout(pUser, BoxLayout.Y_AXIS));
        pAdmin = new JPanel(new FlowLayout());
        pButton = new JPanel(new FlowLayout());
        pCard = new JPanel(cardLayout);
        pAdminb = new JPanel(new FlowLayout());

        bAdmin = new JButton("tryb Administratora");
        bUser = new JButton("Powrót do trybu użytkownika");
        bDFilm = new JButton("Dodaj film");
        bDSeans = new JButton("Dodaj Seans");
        bDSala = new JButton("Dodaj Salę");
        bSeans = new JButton("Pokaż informacje");

        pCard.add(pUser, "User");
        pCard.add(pAdmin, "Admin");

        pAdmin.setVisible(false);
        bUser.setVisible(false);

        actionListener();
        generuj();

        add(pButton, BorderLayout.NORTH);
        add(pCard, BorderLayout.CENTER);
    }

    private void actionListener(){
        bAdmin.addActionListener(e -> openAdmin());
        bUser.addActionListener(e -> openUser());
        bDFilm.addActionListener(e -> dodajFilm());
        bDSeans.addActionListener(e -> dodajSeans());
        bDSala.addActionListener(e -> dodajSala());
        bSeans.addActionListener(e -> pokazSeans((Seans) cbSeanse.getSelectedItem()));
    }

    private void generuj(){
        pUser.add(new JLabel("Panel użytkownika"));
        pUser.add(cbSeanse);
        pUser.add(bSeans);

        pAdmin.add(new JLabel("Panel administratora"));
        pAdmin.add(pAdminb);
        pAdminb.add(bDFilm);
        pAdminb.add(bDSeans);
        pAdminb.add(bDSala);

        pButton.add(bAdmin);
        pButton.add(bUser);
    }

    private void openAdmin(){
        String password = JOptionPane.showInputDialog("Podaj hasło:");
        if (password != null && password.equals("admin")){
            cardLayout.show(pCard, "Admin");
            bUser.setVisible(true);
            bAdmin.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Nieprawidłowe hasło.", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openUser(){
        cardLayout.show(pCard, "User");
        bUser.setVisible(false);
        bAdmin.setVisible(true);
    }

    private void dodajFilm(){
        JPanel panel = new JPanel();
        JTextField tfTytul = new JTextField(10);
        JTextArea taOpis = new JTextArea(5, 20);
        JTextField tfCzas = new JTextField(5);
        JTextField tfWiek = new JTextField(5);

        panel.add(new JLabel("Tytuł:"));
        panel.add(tfTytul);
        panel.add(new JLabel("opis:"));
        panel.add(taOpis);
        panel.add(new JLabel("czas:"));
        panel.add(tfCzas);
        panel.add(new JLabel("minimalny wiek:"));
        panel.add(tfWiek);


        int resoult = JOptionPane.showConfirmDialog(null, panel, "Podaj dane filmu:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resoult == JOptionPane.OK_OPTION){
            String tytul = tfTytul.getText();
            String opis = taOpis.getText();
            int czas;
            int wiek;
            try {
                czas = Integer.parseInt(tfCzas.getText());
                wiek = Integer.parseInt(tfWiek.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Błędne wartości");
                return;
            }

            Film film = new Film(tytul, opis, czas, wiek);
            filmy.add(film);

            JOptionPane.showMessageDialog(null, "Film dodany poyślnie");
        }
    }

    private void dodajSeans(){
        JPanel panel = new JPanel();
        JComboBox<Film> cbFilm = new JComboBox<>(filmy.toArray(new Film[0]));
        JComboBox<Sala> cbSala = new JComboBox<>(sale.toArray(new Sala[0]));
        JTextField tfData = new JTextField(10);
        JTextField tfGodzina = new JTextField(5);
        JTextField tfCena = new JTextField(5);

        panel.add(cbFilm);
        panel.add(new JLabel("Data (RRRR-MM-DD):"));
        panel.add(tfData);
        panel.add(new JLabel("Godzina (HH:MM):"));
        panel.add(tfGodzina);
        panel.add(new JLabel("Cena:"));
        panel.add(tfCena);
        panel.add(cbSala);

        int result = JOptionPane.showConfirmDialog(null, panel, "Podaj dane seansu:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION){
            Film film = (Film) cbFilm.getSelectedItem();
            String data = tfData.getText();
            String godz = tfGodzina.getText();
            int cena;
            LocalDateTime start;
            Sala sala = (Sala) cbSala.getSelectedItem();

            try {
                cena = Integer.parseInt(tfCena.getText());
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Błędne wartości");
                return;
            }

            try {
                start = LocalDateTime.parse(data + "T" + godz + ":00");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Błędne wartości");
                return;
            }

            Seans seans = new Seans(film, start, cena);
            assert sala != null;
            if (sala.dodajSeans(seans)){
                seanse.add(seans);
                seanse.sort(Comparator.comparing(Seans::getStart));
                JOptionPane.showMessageDialog(null, "Seans dodany poyślnie");
            } else {
                JOptionPane.showMessageDialog(null, "Sala jest zajęta");
            }
        }
    }

    private void dodajSala(){
        JPanel panel = new JPanel();
        JTextField tfNumer = new JTextField(10);
        JTextField tfRzedy = new JTextField(10);
        JTextField tfMiejsca = new JTextField(10);

        panel.add(new JLabel("Numer sali:"));
        panel.add(tfNumer);
        panel.add(new JLabel("Liczba rzędów:"));
        panel.add(tfRzedy);
        panel.add(new JLabel("Liczba miejsc w rzędzie:"));
        panel.add(tfMiejsca);

        int result = JOptionPane.showConfirmDialog(null, panel, "Podaj dane sali:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION){
            int numer;
            int rzedy;
            int miejsca;
            try {
                numer = Integer.parseInt(tfNumer.getText());
                rzedy = Integer.parseInt(tfRzedy.getText());
                miejsca = Integer.parseInt(tfMiejsca.getText());
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Błędne wartości");
                return;
            }

            Sala sala = new Sala(numer, rzedy, miejsca);
            sale.add(sala);

            JOptionPane.showMessageDialog(null, "Sala dodana pomyślnie");
        }
    }

    private void pokazSeans(Seans seans){
        JPanel panel = new JPanel();
        JButton kupBilet = new JButton();
        kupBilet.addActionListener(e -> kupBilet(seans));

        panel.add(new JLabel("Tytuł: " + seans.getFilm().getTytul()));
        panel.add(new JLabel("Opis: " + seans.getFilm().getOpis()));
        panel.add(new JLabel("Czas trwania: " + seans.getFilm().getCzas()));
        panel.add(new JLabel("Od lat: " + seans.getFilm().getWiek()));
        panel.add(new JLabel("Godzina seansu: " + seans.getStart()));
        panel.add(new JLabel("Cena biletu: " + seans.getCena()));
        panel.add(kupBilet);
    }

    private void kupBilet(Seans seans){

    }
}
