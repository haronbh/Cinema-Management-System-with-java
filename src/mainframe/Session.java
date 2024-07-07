/*
 * Cliquez sur nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt pour changer cette licence
 * Cliquez sur nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java pour modifier ce modèle
 */
package mainframe;

/**
 * Fenêtre principale pour les opérations de session.
 * Permet d'ajouter, d'afficher et de supprimer des sessions de film.
 * Auteur: haron
 */
public class Session extends javax.swing.JFrame {

    private javax.swing.JButton btnAjouterSession;
    private javax.swing.JButton btnAfficherRechercher;
    private javax.swing.JButton btnSupprimerSession;
    private javax.swing.JLabel lblOperations;
    private javax.swing.JLabel lblBackgroundImage;

    /**
     * Crée une nouvelle forme Session.
     */
    public Session() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Initialise les composants de la fenêtre.
     * Cette méthode est générée automatiquement par le constructeur graphique NetBeans.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnAjouterSession = new javax.swing.JButton();
        btnAfficherRechercher = new javax.swing.JButton();
        btnSupprimerSession = new javax.swing.JButton();
        lblOperations = new javax.swing.JLabel();
        lblBackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Bouton pour ajouter une nouvelle session
        btnAjouterSession.setBackground(new java.awt.Color(153, 255, 255));
        btnAjouterSession.setFont(new java.awt.Font("Times New Roman", 1, 18));
        btnAjouterSession.setForeground(new java.awt.Color(204, 0, 0));
        btnAjouterSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-1-icon.png")));
        btnAjouterSession.setText("Ajouter une Session");
        btnAjouterSession.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAjouterSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterSessionActionPerformed(evt);
            }
        });
        getContentPane().add(btnAjouterSession, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 200, -1));

        // Bouton pour afficher et rechercher des sessions
        btnAfficherRechercher.setBackground(new java.awt.Color(102, 255, 204));
        btnAfficherRechercher.setFont(new java.awt.Font("Narkisim", 1, 18));
        btnAfficherRechercher.setForeground(new java.awt.Color(204, 0, 0));
        btnAfficherRechercher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shooow.png")));
        btnAfficherRechercher.setText("Afficher & Rechercher");
        btnAfficherRechercher.setActionCommand("Afficher & modifier");
        btnAfficherRechercher.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAfficherRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAfficherRechercherActionPerformed(evt);
            }
        });
        getContentPane().add(btnAfficherRechercher, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 210, 30));

        // Bouton pour supprimer une session
        btnSupprimerSession.setBackground(new java.awt.Color(153, 255, 204));
        btnSupprimerSession.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 22));
        btnSupprimerSession.setForeground(new java.awt.Color(204, 0, 0));
        btnSupprimerSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("/deleteicon.png")));
        btnSupprimerSession.setText("Supprimer");
        btnSupprimerSession.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSupprimerSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerSessionActionPerformed(evt);
            }
        });
        getContentPane().add(btnSupprimerSession, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        // Libellé pour le titre des opérations
        lblOperations.setBackground(new java.awt.Color(204, 255, 204));
        lblOperations.setFont(new java.awt.Font("Segoe UI", 1, 36));
        lblOperations.setForeground(new java.awt.Color(204, 255, 204));
        lblOperations.setText("Opérations sur les sessions");
        getContentPane().add(lblOperations, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        // Étiquette d'image de fond
        lblBackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cinemasession.jpg")));
        getContentPane().add(lblBackgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }

    /**
     * Action associée au bouton "Ajouter une Session".
     * Ouvre la fenêtre ScheduleSession pour ajouter une nouvelle session.
     */
    private void btnAjouterSessionActionPerformed(java.awt.event.ActionEvent evt) {
        ScheduleSession scheduleSession = new ScheduleSession();
        scheduleSession.setVisible(true);
    }

    /**
     * Action associée au bouton "Afficher & Rechercher".
     * Ouvre la fenêtre MovieSessions pour afficher et rechercher des sessions.
     */
    private void btnAfficherRechercherActionPerformed(java.awt.event.ActionEvent evt) {
        MovieSessions movieSessions = new MovieSessions();
        movieSessions.setVisible(true);
    }

    /**
     * Action associée au bouton "Supprimer".
     * Ouvre la fenêtre DeleteSession pour supprimer une session.
     */
    private void btnSupprimerSessionActionPerformed(java.awt.event.ActionEvent evt) {
        MovieSessions deleteSession = new MovieSessions();
        deleteSession.setVisible(true);
    }

    /**
     * Méthode principale pour exécuter la fenêtre Session.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Session().setVisible(true);
        });
    }
}
