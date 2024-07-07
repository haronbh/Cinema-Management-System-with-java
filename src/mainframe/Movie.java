/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mainframe;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.GroupLayout;

/**
 * Fenêtre principale pour les opérations sur les films.
 * Permet d'ajouter, afficher et supprimer des films.
 * @author haron
 */
public class Movie extends javax.swing.JFrame {

    /**
     * Constructeur de la classe Movie.
     * Initialise les composants de l'interface utilisateur.
     */
    public Movie() {
        initComponents();
        // Fermer l'application lorsque la fenêtre principale est fermée
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Initialise les composants graphiques de l'interface utilisateur.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        showButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Configuration du bouton "Affichage & recherche"
        showButton.setBackground(new java.awt.Color(0, 0, 255));
        showButton.setFont(new java.awt.Font("Serif", 1, 14));
        showButton.setForeground(new java.awt.Color(255, 255, 255));
        showButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shooow.png")));
        showButton.setText("Affichage & modification");
        showButton.setToolTipText("");
        showButton.setAlignmentY(0.0F);
        showButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showButton.setIconTextGap(1);
        showButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });
        getContentPane().add(showButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 220, 40));

        // Configuration du bouton "Suppression"
        deleteButton.setBackground(new java.awt.Color(0, 0, 255));
        deleteButton.setFont(new java.awt.Font("Serif", 1, 18));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/deleteicon.png")));
        deleteButton.setText("Suppression");
        deleteButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.gray, null, new java.awt.Color(153, 255, 51)));
        deleteButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        deleteButton.setIconTextGap(1);
        deleteButton.setMargin(new java.awt.Insets(2, 0, 0, 0));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 140, 40));

        // Configuration du label de titre
        titleLabel.setBackground(new java.awt.Color(204, 255, 255));
        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 33));
        titleLabel.setForeground(new java.awt.Color(204, 255, 0));
        titleLabel.setText("Opérations sur les films");
        titleLabel.setToolTipText("");
        titleLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, -1));

        // Configuration du bouton "Ajout d'un film"
        addButton.setBackground(new java.awt.Color(0, 0, 255));
        addButton.setFont(new java.awt.Font("Serif", 1, 14));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-1-icon.png")));
        addButton.setText("Ajout d'un film");
        addButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 204)));
        addButton.setMultiClickThreshhold(1L);
        addButton.setName("");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 150, 40));

        // Configuration de l'image d'arrière-plan
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filmbackground.jpg")));
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 370));

        pack();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Ajout d'un film".
     * Ouvre la fenêtre d'ajout d'un film.
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        RegisterMovie rm = new RegisterMovie();
        rm.setVisible(true);
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Affichage & recherche".
     * Ouvre la fenêtre de liste des films.
     */
    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MovieList ml = new MovieList();
        ml.setVisible(true);
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Suppression".
     * Ouvre la fenêtre de liste des films (contient aussi l'option de supression').
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MovieList d1 = new MovieList();
        d1.setVisible(true);
    }

    /**
     * Point d'entrée de l'application.
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Movie().setVisible(true);
            }
        });
    }

    // Déclaration des composants de l'interface utilisateur
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton showButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel backgroundLabel;
}
