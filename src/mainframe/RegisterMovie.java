/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mainframe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 * Fenêtre d'enregistrement d'un nouveau film.
 * Permet à l'utilisateur d'ajouter les détails d'un film, tels que le titre, le casting, le réalisateur, etc.
 */
public class RegisterMovie extends JFrame {

    Connection conn = null;
    private JTextArea taSynopsis;

    /**
     * Lancement de l'application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterMovie frame = new RegisterMovie();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructeur de la classe RegisterMovie.
     * Initialise la fenêtre d'enregistrement d'un nouveau film.
     */
    public RegisterMovie() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Enregistrer un nouveau film");
        setBounds(100, 100, 680, 680);

        // Éléments graphiques de l'interface utilisateur
        JLabel lblTitle = new JLabel("Titre du film");
        lblTitle.setForeground(Color.red);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfMovieTitle = new JTextField();
        tfMovieTitle.setToolTipText("Entrez le titre du film");
        tfMovieTitle.setName("");
        tfMovieTitle.setColumns(30);

        JLabel lblBilledCast = new JLabel("Casting facturé");
        lblBilledCast.setForeground(Color.red);
        lblBilledCast.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfBilledCast = new JTextField();
        tfBilledCast.setToolTipText("Qui sont les acteurs ?");
        tfBilledCast.setColumns(30);

        JLabel lblDirector = new JLabel("Directeur");
        lblDirector.setForeground(Color.red);
        lblDirector.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfDirector = new JTextField();
        tfDirector.setColumns(30);

        JLabel lblReleaseDate = new JLabel("Date de sortie");
        lblReleaseDate.setForeground(Color.red);
        lblReleaseDate.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfReleaseDate = new JTextField();
        tfReleaseDate.setToolTipText("DD-MM-YYYY");
        tfReleaseDate.setColumns(30);

        JLabel lblDuration = new JLabel("Durée");
        lblDuration.setForeground(Color.red);
        lblDuration.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfDuration = new JTextField();
        tfDuration.setToolTipText("159min 56sec");
        tfDuration.setColumns(30);

        JLabel lblGenre = new JLabel("Genre");
        lblGenre.setForeground(Color.red);
        lblGenre.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfGenre = new JTextField();
        tfGenre.setToolTipText("Action, Comédie, Amour..?!");
        tfGenre.setColumns(30);

        JLabel lblRating = new JLabel("Notation");
        lblRating.setForeground(Color.red);
        lblRating.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfRating = new JTextField();
        tfRating.setToolTipText("Donnez votre note de 0 à 5,0");
        tfRating.setColumns(30);

        JLabel lblDistributor = new JLabel("Distributeur");
        lblDistributor.setForeground(Color.red);
        lblDistributor.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfDistributor = new JTextField();
        tfDistributor.setColumns(30);

        JLabel lblWebsite = new JLabel("site");
        lblWebsite.setForeground(Color.red);
        lblWebsite.setFont(new Font("Serif", Font.BOLD, 14));

        JTextField tfWebsite = new JTextField();
        tfWebsite.setToolTipText("site officiel");
        tfWebsite.setColumns(30);

        JLabel lblSynopsis = new JLabel("Synopsis");
        lblSynopsis.setForeground(Color.red);
        lblSynopsis.setFont(new Font("Serif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane();

        JButton btnRegister = new JButton("Ajouter");
        btnRegister.setBackground(new java.awt.Color(0, 0, 255));
        btnRegister.setFont(new java.awt.Font("Serif", 1, 14)); 
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn = MySQLConnection.dbConnector();
                    String query1 = "INSERT INTO regMovie (title, billcast, director, releasedate, duration, genre, rating, distributor, website, synopsis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement pst1 = conn.prepareStatement(query1);
                    pst1.setString(1, tfMovieTitle.getText());
                    pst1.setString(2, tfBilledCast.getText());
                    pst1.setString(3, tfDirector.getText());
                    pst1.setString(4, tfReleaseDate.getText());
                    pst1.setString(5, tfDuration.getText());
                    pst1.setString(6, tfGenre.getText());
                    pst1.setString(7, tfRating.getText());
                    pst1.setString(8, tfDistributor.getText());
                    pst1.setString(9, tfWebsite.getText());
                    pst1.setString(10, taSynopsis.getText());

                    pst1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "enregistré avec succès");

                    // Effacer les champs après l'enregistrement
                    tfMovieTitle.setText("");
                    tfBilledCast.setText("");
                    tfDirector.setText("");
                    tfReleaseDate.setText("");
                    tfDuration.setText("");
                    tfGenre.setText("");
                    tfRating.setText("");
                    tfDistributor.setText("");
                    tfWebsite.setText("");
                    taSynopsis.setText("");

                    pst1.close();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

        // Agencement des composants dans la fenêtre
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(lblTitle)
                                        .addComponent(lblBilledCast)
                                        .addComponent(lblDirector)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblSynopsis)
                                                        .addComponent(lblReleaseDate)
                                                        .addComponent(lblGenre)
                                                        .addComponent(lblRating)
                                                        .addComponent(lblDuration)
                                                        .addComponent(lblDistributor)
                                                        .addComponent(lblWebsite))
                                                .addGap(38)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(tfGenre, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                                                        .addComponent(tfDuration, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                                                        .addComponent(tfReleaseDate, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                                                        .addComponent(tfDirector, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                                                        .addComponent(tfBilledCast, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                                                        .addComponent(tfMovieTitle, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)))
                                                        .addComponent(tfDistributor)
                                                        .addComponent(tfWebsite)
                                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                                        .addComponent(tfRating)
                                                        .addComponent(btnRegister, Alignment.TRAILING))))
                                .addGap(499))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTitle)
                                        .addComponent(tfMovieTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBilledCast)
                                        .addComponent(tfBilledCast, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblDirector)
                                        .addComponent(tfDirector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblReleaseDate)
                                        .addComponent(tfReleaseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(tfDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDuration))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblGenre)
                                        .addComponent(tfGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblRating)
                                        .addComponent(tfRating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(tfDistributor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDistributor))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(tfWebsite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblWebsite))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblSynopsis)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addComponent(btnRegister)
                                .addContainerGap(62, Short.MAX_VALUE))
        );

        // Zone de saisie pour le synopsis
        taSynopsis = new JTextArea();
        taSynopsis.setToolTipText("Thème du film");
        taSynopsis.setLineWrap(true);
        taSynopsis.setWrapStyleWord(true);
        scrollPane.setViewportView(taSynopsis);
        getContentPane().setLayout(groupLayout);
        getContentPane().setBackground(new Color(255, 217, 0));
    }
}
