import javax.swing.*;
import java.awt.*;

public class Okno extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel pUser, pAdmin, pButton, pCard;
    private final JButton bAdmin, bUser;

    public Okno(){
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();

        pUser = new JPanel(new FlowLayout());
        pAdmin = new JPanel(new FlowLayout());
        pButton = new JPanel(new FlowLayout());
        pCard = new JPanel(cardLayout);

        bAdmin = new JButton("tryb Administratora");
        bUser = new JButton("Powrót do trybu użytkownika");

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
    }

    private void generuj(){
        pUser.add(new JLabel("Panel użytkownika"));

        pAdmin.add(new JLabel("Panel administratora"));

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
}
