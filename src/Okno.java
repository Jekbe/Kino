import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class Okno extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel pUser, pAdmin, pButton, pCard, pAdminb;
    private final JButton bAdmin, bUser, bDFilm, bDSeans, bDSala, bSeans, bUBilet, bZapisz;
    private List<Sala> sale = new ArrayList<>();
    private List<Film> filmy = new ArrayList<>();
    private List<Seans> seanse = new ArrayList<>();
    private List<Bilet> bilety = new ArrayList<>();
    JComboBox<Seans> cbSeanse = new JComboBox<>(seanse.toArray(new Seans[0]));
    JComboBox<Bilet> cbBilety = new JComboBox<>(bilety.toArray(new Bilet[0]));

    public Okno(){
        odczyt();
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();

        pUser = new JPanel(new FlowLayout());
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
        bUBilet = new JButton("Usuń bilet");
        bZapisz = new JButton("Zapisz dane");

        pCard.add(pUser, "User");
        pCard.add(pAdmin, "Admin");

        cardLayout.show(pCard, "User");
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
        bUBilet.addActionListener(e -> usunBilet((Bilet) cbBilety.getSelectedItem()));
        bZapisz.addActionListener(e -> zapisz());
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
        pAdmin.add(new JLabel("Bilety: "));
        pAdmin.add(cbBilety);
        pAdmin.add(bUBilet);
        pAdmin.add(bZapisz);

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
        JTextField tfTytul = new JTextField(10), tfCzas = new JTextField(5), tfWiek = new JTextField(5);
        JTextArea taOpis = new JTextArea(5, 20);


        panel.add(new JLabel("Tytuł:"));
        panel.add(tfTytul);
        panel.add(new JLabel("opis:"));
        panel.add(taOpis);
        panel.add(new JLabel("czas:"));
        panel.add(tfCzas);
        panel.add(new JLabel("minimalny wiek:"));
        panel.add(tfWiek);


        int resoult = JOptionPane.showConfirmDialog(null, panel, "Nowy film:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resoult == JOptionPane.OK_OPTION){
            String tytul = tfTytul.getText(), opis = taOpis.getText();
            int czas, wiek;
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
        JTextField tfData = new JTextField(10), tfGodzina = new JTextField(5), tfCena = new JTextField(5);

        panel.add(cbFilm);
        panel.add(new JLabel("Data (RRRR-MM-DD):"));
        panel.add(tfData);
        panel.add(new JLabel("Godzina (HH:MM):"));
        panel.add(tfGodzina);
        panel.add(new JLabel("Cena:"));
        panel.add(tfCena);
        panel.add(cbSala);

        int result = JOptionPane.showConfirmDialog(null, panel, "Nowy Seans:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION){
            Film film = (Film) cbFilm.getSelectedItem();
            String data = tfData.getText(), godz = tfGodzina.getText();
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

            Seans seans = new Seans(film, start, cena, sala);
            assert sala != null;
            if (sala.dodajSeans(seans)){
                seanse.add(seans);
                seanse.sort(Comparator.comparing(Seans::start));
                cbSeanse.setModel(new DefaultComboBoxModel<>(seanse.toArray(new Seans[0])));
                JOptionPane.showMessageDialog(null, "Seans dodany poyślnie");
            } else {
                JOptionPane.showMessageDialog(null, "Sala jest zajęta");
            }
        }
    }

    private void dodajSala(){
        JPanel panel = new JPanel();
        JTextField tfNumer = new JTextField(10), tfRzedy = new JTextField(10), tfMiejsca = new JTextField(10);

        panel.add(new JLabel("Numer sali:"));
        panel.add(tfNumer);
        panel.add(new JLabel("Liczba rzędów:"));
        panel.add(tfRzedy);
        panel.add(new JLabel("Liczba miejsc w rzędzie:"));
        panel.add(tfMiejsca);

        int result = JOptionPane.showConfirmDialog(null, panel, "Nowa sala:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION){
            int numer, rzedy, miejsca;
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
        try {
            JPanel panel = new JPanel();
            JButton kupBilet = new JButton("Kup bilet");
            kupBilet.addActionListener(e -> kupBilet(seans));

            panel.add(new JLabel("Tytuł: " + seans.film().tytul()));
            panel.add(new JLabel("Opis: " + seans.film().opis()));
            panel.add(new JLabel("Czas trwania: " + seans.film().czas()));
            panel.add(new JLabel("Od lat: " + seans.film().wiek()));
            panel.add(new JLabel("Godzina seansu: " + seans.start()));
            panel.add(new JLabel("Sala nr.: " + seans.sala().getNumer()));
            panel.add(new JLabel("Cena biletu: " + seans.cena()));
            panel.add(kupBilet);

            JOptionPane.showMessageDialog(null, panel, "informacje", JOptionPane.PLAIN_MESSAGE);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Nie wybrano seansu");
        }

    }

    private void kupBilet(Seans seans){
        if (seans.film().tylkoDlaDoroslych()){
            JOptionPane.showMessageDialog(null, "Film dla dorosłych! Zapytaj o wiek!");
        }
        JPanel panel = new JPanel();
        JTextField tfRzad = new JTextField(5), tfMiejsce = new JTextField(5), tfWlasciciel = new JTextField(20);

        panel.add(new JLabel("Rząd:"));
        panel.add(tfRzad);
        panel.add(new JLabel("Miejsce:"));
        panel.add(tfMiejsce);
        panel.add(new JLabel("Właściciel:"));
        panel.add(tfWlasciciel);

        panel.add(new JLabel("Wolne miejsca: "));
        StringBuilder siedzenia = new StringBuilder("<html>");
        for (int f1 = 0; f1 < seans.sala().getRzedy(); f1++){
            siedzenia.append("r").append(f1 + 1).append(": ");
            for (int f2 = 0; f2 < seans.sala().getMiejsca(); f2++){
                if (seans.sala().getSiedzenie(f1, f2).getWolne()){
                    siedzenia.append("<font color='green'>").append(f2 + 1).append(" </font>");
                } else {
                    siedzenia.append("<font color='red'>").append(f2 + 1).append(" </font>");
                }
            }
            siedzenia.append("<br>");
        }
        siedzenia.append("</html>");

        panel.add(new JLabel(siedzenia.toString()));

        int result = JOptionPane.showConfirmDialog(this, panel, "Kup bilet", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION){
            int rzad, miejsce;
            String wlasciciel = tfWlasciciel.getText();

            try {
                rzad = Integer.parseInt(tfRzad.getText()) - 1;
                miejsce = Integer.parseInt(tfMiejsce.getText()) - 1;
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Błędne wartości");
                return;
            }

            if (seans.sala().getSiedzenie(rzad, miejsce).getWolne()){
                seans.sala().getSiedzenie(rzad, miejsce).zajmij();
                Bilet bilet = new Bilet(seans.sala(), seans, rzad, miejsce, wlasciciel);

                bilety.add(bilet);
                cbBilety.setModel(new DefaultComboBoxModel<>(bilety.toArray(new Bilet[0])));

                JOptionPane.showMessageDialog(null, "Bilet dodany pomyślnie");
            } else {
                JOptionPane.showMessageDialog(null, "To miejsce jest już zajęte");
            }
        }
    }

    private void usunBilet(Bilet bilet){
        try {
            bilet.getSiedzenie().zwolnij();
            bilety.remove(bilet);
            cbBilety.setModel(new DefaultComboBoxModel<>(bilety.toArray(new Bilet[0])));
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Nie wybrano biletu");
        }

    }

    private void zapisz(){
        try (
                ObjectOutputStream pFilmy = new ObjectOutputStream(new FileOutputStream("Filmy.ser"));
                ObjectOutputStream pSeanse = new ObjectOutputStream(new FileOutputStream("Seanse.ser"));
                ObjectOutputStream pSale = new ObjectOutputStream(new FileOutputStream("Sale.ser"));
                ObjectOutputStream pBilety = new ObjectOutputStream(new FileOutputStream("Bilety.ser"))
        ){
            pFilmy.writeObject(filmy);
            pSeanse.writeObject(seanse);
            pSale.writeObject(sale);
            pBilety.writeObject(bilety);
        } catch (IOException e){
            System.out.println("Błąd: " + e);
        }
    }

    @SuppressWarnings("unchecked")
    private void odczyt(){
        try (
                ObjectInputStream pFilmy = new ObjectInputStream(new FileInputStream("Filmy.ser"));
                ObjectInputStream pSeanse = new ObjectInputStream(new FileInputStream("Seanse.ser"));
                ObjectInputStream pSale = new ObjectInputStream(new FileInputStream("Sale.ser"));
                ObjectInputStream pBilety = new ObjectInputStream(new FileInputStream("Bilety.ser"))
        ){
            filmy = (List<Film>) pFilmy.readObject();
            seanse = (List<Seans>) pSeanse.readObject();
            sale = (List<Sala>) pSale.readObject();
            bilety = (List<Bilet>) pBilety.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Błąd: " + e);
        }
    }
}
