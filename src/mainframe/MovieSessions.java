package mainframe;

import java.awt.EventQueue;
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

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import net.proteanit.sql.DbUtils;

public class MovieSessions extends JFrame {

    private JComboBox<String> comboBox;
    private Connection conn = null;
    private JTable table;

public void filldate() {
    try {
        String query = "SELECT SecDate from secMovie";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Clear existing items in the comboBox
        comboBox.removeAllItems();

        while (rs.next()) {
            comboBox.addItem(rs.getString("SecDate"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    

    public MovieSessions() {
        setTitle("Afficher les sessions de cinéma");
        setBounds(100, 100, 870, 471);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JLabel lblSelectDate = new JLabel("Sélectionner une date");
        lblSelectDate.setBackground(new java.awt.Color(255, 10, 10));
        lblSelectDate.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblSelectDate.setForeground(new java.awt.Color(255, 10, 10));
        lblSelectDate.setText("Sélectionner une date");
        comboBox = new JComboBox<>();

        conn = MySQLConnection.dbConnector();
        table = new JTable();

        JButton btnLoadData = new JButton("Charger des données");
        btnLoadData.setBackground(new java.awt.Color(153, 255, 255));
        btnLoadData.setFont(new java.awt.Font("Times New Roman", 1, 16));
        btnLoadData.setForeground(new java.awt.Color(204, 0, 0));
        btnLoadData.setText("Afficher les sessions");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "select MovieTitle, SecTime from secMovie WHERE SecDate=?";
                    PreparedStatement pat = conn.prepareStatement(query);
                    pat.setString(1, (String) comboBox.getSelectedItem());
                    ResultSet rs = pat.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    // Sélectionne automatiquement la première ligne après le chargement des données
                    if (table.getRowCount() > 0) {
                        table.setRowSelectionInterval(0, 0);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnModify = new JButton("Modifier la session");
        btnModify.setBackground(new java.awt.Color(255, 204, 102));
        btnModify.setFont(new java.awt.Font("Times New Roman", 1, 16));
        btnModify.setForeground(new java.awt.Color(0, 102, 0));
        btnModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                modifySession();
            }
        });
        JButton btnDeleteSession = new JButton("Supprimer la session");
btnDeleteSession.setBackground(new java.awt.Color(255, 0, 0));
btnDeleteSession.setFont(new java.awt.Font("Times New Roman", 1, 16));
btnDeleteSession.setForeground(new java.awt.Color(255, 255, 255));
btnDeleteSession.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent arg0) {
        deleteSession();
    }
});

GroupLayout groupLayout = new GroupLayout(getContentPane());
groupLayout.setHorizontalGroup(
    groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(lblSelectDate)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnLoadData)
                    .addGap(18)
                    .addComponent(btnModify)
                    .addGap(18)
                    .addComponent(btnDeleteSession)) // Added the "Delete" button here
                .addComponent(table, GroupLayout.PREFERRED_SIZE, 654, GroupLayout.PREFERRED_SIZE))
            .addContainerGap(13, Short.MAX_VALUE))
);
groupLayout.setVerticalGroup(
    groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(lblSelectDate)
                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnLoadData)
                .addComponent(btnModify)
                .addComponent(btnDeleteSession)) // Added the "Delete" button here
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(table, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
            .addContainerGap(15, Short.MAX_VALUE))
);
getContentPane().setLayout(groupLayout);

        filldate();
    }

private void modifySession() {
    String selectedDate = (String) comboBox.getSelectedItem();
    
    // Vérifiez d'abord si une date est sélectionnée dans le combo box
    if (selectedDate != null) {
        // Exemple de boîte de dialogue pour saisir de nouvelles données :
        String newSessionTime = JOptionPane.showInputDialog(this, "Entrez le nouveau temps de session pour la date '" + selectedDate + "':");

        // Après avoir obtenu les nouvelles données, vous pouvez les utiliser pour mettre à jour la base de données.
        // Exemple de mise à jour de la base de données (c'est un exemple simple, vous devrez adapter cela à votre structure de base de données) :
        if (newSessionTime != null && !newSessionTime.isEmpty()) {
            try {
                String updateQuery = "UPDATE secMovie SET SecTime=? WHERE SecDate=?";
                try (PreparedStatement updatePst = conn.prepareStatement(updateQuery)) {
                    updatePst.setString(1, newSessionTime);
                    updatePst.setString(2, selectedDate);

                    int rowsAffected = updatePst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "La session a été mise à jour avec succès.");
                        // Vous pouvez également mettre à jour l'affichage de la table si nécessaire.
                        refreshTable(selectedDate);
                    } else {
                        JOptionPane.showMessageDialog(this, "Aucune mise à jour effectuée. Vérifiez les données et réessayez.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour de la session.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez saisir une nouvelle valeur pour le temps de session.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Veuillez sélectionner une date.");
    }
}
private void deleteSession() {
    try {
        String selectedDate = (String) comboBox.getSelectedItem();
        if (selectedDate != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cette session?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                // Implémentez la logique de suppression dans la base de données ici
                String deleteQuery = "DELETE FROM secMovie WHERE SecDate=?";
                try (PreparedStatement deletePst = conn.prepareStatement(deleteQuery)) {
                    deletePst.setString(1, selectedDate);
                    int rowsAffected = deletePst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Session supprimée avec succès");
                        // Clear and refill the comboBox with the updated list of sessions
                        comboBox.removeAllItems();
                        filldate();
                    } else {
                        JOptionPane.showMessageDialog(this, "Aucune session supprimée. Vérifiez les données et réessayez.");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une session à supprimer");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Méthode pour rafraîchir la table après la modification
private void refreshTable(String selectedDate) {
    try {
        String query = "select MovieTitle, SecTime from secMovie WHERE SecDate=?";
        PreparedStatement pat = conn.prepareStatement(query);
        pat.setString(1, selectedDate);
        ResultSet rs = pat.executeQuery();
        table.setModel(DbUtils.resultSetToTableModel(rs));

        // Sélectionne automatiquement la première ligne après le chargement des données
        if (table.getRowCount() > 0) {
            table.setRowSelectionInterval(0, 0);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

