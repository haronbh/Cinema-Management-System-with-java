/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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

public class MainClass extends javax.swing.JFrame {

    // Déclaration des composants de l'interface utilisateur
    private javax.swing.JButton btnMovies;
    private javax.swing.JButton btnReservations;
    private javax.swing.JButton btnSessions;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JLabel jLabelBackground;

    // Constructeur de la classe
    public MainClass() {
        initComponents();
        // Fermer l'application lorsque la fenêtre principale est fermée
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // Méthode générée par NetBeans pour initialiser les composants
    private void initComponents() {

        btnMovies = new javax.swing.JButton();
        btnReservations = new javax.swing.JButton();
        btnSessions = new javax.swing.JButton();
        jLabelWelcome = new javax.swing.JLabel();
        jLabelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Configuration du bouton Films
        btnMovies.setFont(new java.awt.Font("Times New Roman", 1, 24));
        btnMovies.setForeground(new java.awt.Color(255, 51, 51));
        btnMovies.setIcon(new javax.swing.ImageIcon(getClass().getResource("/film.png")));
        btnMovies.setText("Films");
        btnMovies.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 0)));
        btnMovies.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnMovies.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMovies.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoviesActionPerformed(evt);
            }
        });
        getContentPane().add(btnMovies, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 100, 40));

        // Configuration du bouton Réservations
        btnReservations.setBackground(new java.awt.Color(204, 255, 204));
        btnReservations.setFont(new java.awt.Font("Times New Roman", 1, 24));
        btnReservations.setForeground(new java.awt.Color(255, 0, 255));
        btnReservations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reserv.png")));
        btnReservations.setText("Réservations");
        btnReservations.setMargin(new java.awt.Insets(2, 2, 3, 2));
        btnReservations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservationsActionPerformed(evt);
            }
        });
        getContentPane().add(btnReservations, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 200, -1));

        // Configuration du bouton Sessions
        btnSessions.setBackground(new java.awt.Color(255, 204, 204));
        btnSessions.setFont(new java.awt.Font("Times New Roman", 1, 24));
        btnSessions.setForeground(new java.awt.Color(204, 0, 0));
        btnSessions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sess.png")));
        btnSessions.setText("Sessions");
        btnSessions.setMargin(new java.awt.Insets(2, 0, 3, 0));
        btnSessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionsActionPerformed(evt);
            }
        });
        getContentPane().add(btnSessions, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 150, -1));

        // Configuration du label de bienvenue
        jLabelWelcome.setBackground(new java.awt.Color(0, 0, 0));
        jLabelWelcome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20));
        jLabelWelcome.setForeground(new java.awt.Color(102, 255, 102));
        jLabelWelcome.setText("Bienvenue au programme de gestion de cinéma");
        getContentPane().add(jLabelWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        // Configuration de l'image d'arrière-plan
        jLabelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mainframe.jpg")));
        getContentPane().add(jLabelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 470, 420));

        pack();
    }

    // Méthode appelée lorsqu'on clique sur le bouton Réservations
    private void btnReservationsActionPerformed(java.awt.event.ActionEvent evt) {
        Reservation b1 = new Reservation();
        b1.setVisible(true);
    }

    // Méthode appelée lorsqu'on clique sur le bouton Films
    private void btnMoviesActionPerformed(java.awt.event.ActionEvent evt) {
        Movie movieFrame = new Movie();
        movieFrame.setVisible(true);
    }

    // Méthode appelée lorsqu'on clique sur le bouton Sessions
    private void btnSessionsActionPerformed(java.awt.event.ActionEvent evt) {
        Session sessionFrame = new Session();
        sessionFrame.setVisible(true);
    }

    // Point d'entrée de l'application
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainClass().setVisible(true);
            }
        });
    }
}
