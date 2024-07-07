/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mainframe;

import javax.swing.ImageIcon;

/**
 * Fenêtre principale pour les opérations sur les réservations.
 * Permet à l'utilisateur d'ajouter, afficher et supprimer des réservations.
 * Utilise des boutons pour rediriger vers d'autres fenêtres dédiées à chaque opération.
 */
public class Reservation extends javax.swing.JFrame {

    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblBackgroundImage;

    /**
     * Constructeur de la classe Reservation.
     * Initialise la fenêtre principale pour les opérations sur les réservations.
     */
    public Reservation() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Méthode pour initialiser les composants de l'interface utilisateur.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnAdd = new javax.swing.JButton();
        btnDisplay = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblBackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Bouton pour ajouter une réservation
        btnAdd.setBackground(new java.awt.Color(204, 255, 204));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnAdd.setForeground(new java.awt.Color(255, 0, 0));
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/add-1-icon.png")));
        btnAdd.setText("Ajouter");
        btnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAdd.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnAddActionPerformed(evt);
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        // Bouton pour afficher les réservations
        btnDisplay.setBackground(new java.awt.Color(204, 255, 204));
        btnDisplay.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnDisplay.setForeground(new java.awt.Color(255, 0, 0));
        btnDisplay.setIcon(new ImageIcon(getClass().getResource("/shooow.png")));
        btnDisplay.setText("Afficher et modifier");
        btnDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDisplay.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnDisplayActionPerformed(evt);
        });
        getContentPane().add(btnDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        // Bouton pour supprimer une réservation
        btnDelete.setBackground(new java.awt.Color(204, 255, 204));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/deleteicon.png")));
        btnDelete.setText("Supprimer");
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDelete.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnDeleteActionPerformed(evt);
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, -1, -1));

        // Libellé du titre
        lblTitle.setFont(new java.awt.Font("Rockwell Condensed", 0, 36));
        lblTitle.setText("Opérations sur les réservations");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 360, 50));

        // Image de fond
        lblBackgroundImage.setIcon(new ImageIcon(getClass().getResource("/CINEMA TICKET.jpg")));
        getContentPane().add(lblBackgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 340));

        pack();
    }

    /**
     * Action associée au bouton "Ajouter".
     * Ouvre la fenêtre pour ajouter une réservation.
     */
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        AddReservation addReservationForm = new AddReservation();
        addReservationForm.setVisible(true);
    }

    /**
     * Action associée au bouton "Afficher".
     * Ouvre la fenêtre pour afficher les réservations.
     */
    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {
        DisplayReservations displayReservationsForm = new DisplayReservations();
        displayReservationsForm.setVisible(true);
    }

    /**
     * Action associée au bouton "Supprimer".
     * Ouvre la fenêtre pour supprimer une réservation.
     */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        DisplayReservations deleteReservationForm = new DisplayReservations();
        deleteReservationForm.setVisible(true);
    }

    /**
     * Méthode principale pour lancer l'application.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Reservation().setVisible(true);
        });
    }
}
