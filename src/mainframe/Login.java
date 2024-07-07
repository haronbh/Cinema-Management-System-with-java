package mainframe;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Image;

public class Login {

    private JFrame frmLoginCinema;  // La fenêtre de connexion
    private Connection conn = null;  // La connexion à la base de données
    private JTextField tfUsername;  // Champ de texte pour le nom d'utilisateur
    private JPasswordField passwordField;  // Champ de texte pour le mot de passe

    // Point d'entrée de l'application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frmLoginCinema.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructeur de la classe Login
    public Login() {
        initialize();  // Initialisation de l'interface
        frmLoginCinema.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fermer l'application lorsque la fenêtre est fermée
        conn = MySQLConnection.dbConnector();  // Établir la connexion à la base de données
    }

    // Initialisation des composants graphiques de l'interface
    private void initialize() {
    frmLoginCinema = new JFrame();
    frmLoginCinema.setTitle("Connexion - Système de gestion de cinéma");
    frmLoginCinema.setBounds(100, 100, 820, 330);
    frmLoginCinema.setLocationRelativeTo(null);

    JLabel lblUsername = new JLabel("Nom d'utilisateur");
    JLabel lblPassword = new JLabel("Mot de passe");
    tfUsername = new JTextField();
    tfUsername.setColumns(10);

    JLabel lblCinemaManagementSystem = new JLabel("Système de gestion de cinéma");
    lblCinemaManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 24));

    passwordField = new JPasswordField();

    JButton btnLogin = new JButton("Connexion");
    btnLogin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            // Logique de connexion lorsque le bouton de connexion est cliqué
            try {
                String query = "SELECT * FROM users WHERE username=? and password=?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, tfUsername.getText());
                pst.setString(2, new String(passwordField.getPassword()));

                ResultSet rs = pst.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count++;
                }

                if (count == 1) {
                    // Si un utilisateur est trouvé, fermer la fenêtre de connexion et afficher la fenêtre principale
                    frmLoginCinema.dispose();
                    MainClass mf = new MainClass();
                    mf.setVisible(true);
                } else if (count > 1) {
                    JOptionPane.showMessageDialog(null, "Utilisateur trouvé");
                } else {
                    JOptionPane.showMessageDialog(null, "Identifiants incorrects, veuillez réessayer !");
                }

                rs.close();
                pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    });

    JLabel label = new JLabel("");
    frmLoginCinema.getContentPane().setBackground(new Color(230, 230, 230)); // Couleur de fond claire

    lblUsername.setForeground(Color.red);
    lblPassword.setForeground(Color.red);

    tfUsername.setBackground(Color.yellow);
    passwordField.setBackground(Color.yellow);

    btnLogin.setBackground(new Color(50, 150, 50)); // Couleur verte pour le bouton
    btnLogin.setForeground(Color.WHITE);
    btnLogin.setFont(new Font("Arial", Font.BOLD, 16));

    JLabel lblForStaff = new JLabel("Projet tp GL");
    lblForStaff.setFont(new Font("Tahoma", Font.BOLD, 16));

    GroupLayout groupLayout = new GroupLayout(frmLoginCinema.getContentPane());
    groupLayout.setHorizontalGroup(
        groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(lblCinemaManagementSystem)
                    .addComponent(lblForStaff))
                .addContainerGap(346, Short.MAX_VALUE))
            .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(lblPassword)
                            .addComponent(lblUsername))
                        .addGap(20)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                            .addComponent(tfUsername, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)))
                    .addComponent(btnLogin))
                .addGap(87))
    );
    groupLayout.setVerticalGroup(
        groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGap(45)
                        .addComponent(lblCinemaManagementSystem)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblForStaff)))
                .addGap(31)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(tfUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(16)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addComponent(btnLogin)
                .addGap(73))
    );

    frmLoginCinema.getContentPane().setLayout(groupLayout);
    frmLoginCinema.getContentPane().setBackground(new Color(207, 192, 153));
}
}