package mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class DisplayReservations extends JFrame {

    // Connexion à la base de données
    private Connection conn = null;
    // Tableau pour afficher les données
    private JTable table;

    // Constructeur de la classe
    public DisplayReservations() {
        // Titre de la fenêtre
        setTitle("Afficher et Modifier les réservations");
        // Taille de la fenêtre
        setSize(660, 300);
        // Fermer la fenêtre lorsqu'on clique sur la croix
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Initialisation de la connexion à la base de données
        conn = MySQLConnection.dbConnector();
        // Création d'une table pour afficher les données
        table = new JTable();

        // Ajout d'une barre de défilement à la table
        JScrollPane scrollPane = new JScrollPane(table);
        configureScrollPane(scrollPane);

        // Bouton pour modifier une réservation
        JButton editButton = new JButton("Modifier la réservation");
        editButton.setBackground(new Color(50, 150, 50));
        editButton.setForeground(Color.BLUE);
        editButton.setFont(new Font("Arial", Font.BOLD, 16));
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editReservation();
            }
        });
// un bouton de suppression
JButton deleteButton = new JButton("Supprimer la réservation");
deleteButton.setBackground(new Color(204, 0, 51)); // Couleur rouge pour le bouton
deleteButton.setForeground(Color.red);
deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
deleteButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent arg0) {
        deleteSelectedReservation();
    }
});
        // Panneau pour le bouton de modification
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);

        // Configuration de la disposition de la fenêtre
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Chargement initial des données dans la table
        loadData();
    }

    // Configuration de la barre de défilement
    private void configureScrollPane(JScrollPane scrollPane) {
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }

    // Chargement initial des données depuis la base de données
    private void loadData() {
        try {
            String query = "SELECT id, date, name, regmovie_id, secmovie_id FROM reserv";
            try (PreparedStatement pst = conn.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {

                // Configuration de la table avec les données
                configureTable(rs);
                // Configuration de l'apparence de la table
                configureTableAppearance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Configuration de la table avec les données
    private void configureTable(ResultSet rs) {
        table.setModel(DbUtils.resultSetToTableModel(rs));
    }

    // Configuration de l'apparence de la table
    private void configureTableAppearance() {
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Définition de la largeur préférée de chaque colonne
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);

        // Configuration des couleurs de la table
        table.setForeground(Color.BLACK);
        table.setBackground(Color.YELLOW);
        table.getTableHeader().setBackground(Color.GREEN);
    }

    // Méthode pour modifier une réservation
    private void editReservation() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString();

            // Pour chaque colonne sauf la première (id)
            for (int i = 1; i < table.getColumnCount(); i++) {
                String columnName = table.getColumnName(i);
                String currentValue = table.getValueAt(selectedRow, i).toString();
                String newValue = JOptionPane.showInputDialog(this, "Entrez une nouvelle valeur pour " + columnName + ":", currentValue);

                if (newValue != null) {
                    try {
                        // Requête SQL pour mettre à jour la valeur dans la base de données
                        String updateQuery = "UPDATE reserv SET " + columnName + "=? WHERE id=?";
                        try (PreparedStatement updatePst = conn.prepareStatement(updateQuery)) {
                            updatePst.setString(1, newValue);
                            updatePst.setString(2, id);
                            updatePst.executeUpdate();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Recharger les données après la modification
            loadData();
        } else {
            JOptionPane.showMessageDialog(this, "Sélectionner une colonne pour la modifier");
        }
    }
private void deleteSelectedReservation() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        int reservationId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        int confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cette réservation?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                // Suppression directe
                String deleteQuery = "DELETE FROM reserv WHERE id=?";
                try (PreparedStatement deletePst = conn.prepareStatement(deleteQuery)) {
                    deletePst.setInt(1, reservationId);
                    deletePst.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Réservation supprimée avec succès");
                loadData(); // Rechargez les données après la suppression
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de la réservation.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Sélectionner une réservation à supprimer");
    }
}

    // Méthode principale pour exécuter l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Utiliser le look and feel du système
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                // Création de l'instance de la fenêtre
                DisplayReservations frame = new DisplayReservations();
                // Rendre la fenêtre visible
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
