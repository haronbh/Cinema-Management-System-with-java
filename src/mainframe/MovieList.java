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

import javax.swing.JTable;
import javax.swing.JComboBox;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mainframe.MySQLConnection;

public class MovieList extends JFrame {

    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JButton btnUpdate;
    private JButton btnDelete;

    private Connection conn = null;
    private JTextField tfCast;
    private JTextField tfDirector;
    private JTextField tfReleaseDate;
    private JTextField tfDuration;
    private JTextField tfGenre;
    private JTextField tfRating;
    private JTextField tfDistributor;
    private JTextField tfWebsite;
    private JTextArea textArea;
    private String mt;
    private String st;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MovieList frame = new MovieList();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void filltitle() {
        try {
            String query = "SELECT title from regMovie";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                comboBox.addItem(rs.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filldate() {
        try {
            String query1 = "SELECT SecDate from secMovie WHERE MovieTitle=? ";
            PreparedStatement pst1 = conn.prepareStatement(query1);

            pst1.setString(1, mt);
            ResultSet rs1 = pst1.executeQuery();

            while (rs1.next()) {
                comboBox1.addItem(rs1.getString("SecDate"));
                st = rs1.getString("SecDate");
                comboBox2.removeAllItems();
                filltime();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

private void updateMovieInfo() {
    try {
        // Vous pouvez ajouter une vérification ici pour vous assurer qu'un film est sélectionné
        if (comboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un film à mettre à jour.");
            return;
        }

        // Affichez les valeurs qui vont être mises à jour pour déboguer
        System.out.println("Title: " + comboBox.getSelectedItem());
        System.out.println("Director: " + tfDirector.getText());
        System.out.println("Billcast: " + tfCast.getText());
        System.out.println("Release Date: " + tfReleaseDate.getText());
        System.out.println("Duration: " + tfDuration.getText());
        System.out.println("Genre: " + tfGenre.getText());
        System.out.println("Rating: " + tfRating.getText());
        System.out.println("Distributor: " + tfDistributor.getText());
        System.out.println("Website: " + tfWebsite.getText());
        System.out.println("Synopsis: " + textArea.getText());

        // Demander à l'utilisateur de confirmer la mise à jour
        int confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment mettre à jour les informations du film?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) {
            System.out.println("modification annulé");
            return;
        }

        // Demander à l'utilisateur de saisir de nouvelles données
        String newDirector = JOptionPane.showInputDialog(this, "Entrez le nouveau directeur:", tfDirector.getText());
        String newBillcast = JOptionPane.showInputDialog(this, "Entrez le nouveau casting:", tfCast.getText());
        String newReleaseDate = JOptionPane.showInputDialog(this, "Entrez la nouvelle date de sortie:", tfReleaseDate.getText());
        String newDuration = JOptionPane.showInputDialog(this, "Entrez la nouvelle durée:", tfDuration.getText());
        String newGenre = JOptionPane.showInputDialog(this, "Entrez le nouveau genre:", tfGenre.getText());
        String newRating = JOptionPane.showInputDialog(this, "Entrez la nouvelle notation:", tfRating.getText());
        String newDistributor = JOptionPane.showInputDialog(this, "Entrez le nouveau distributeur:", tfDistributor.getText());
        String newWebsite = JOptionPane.showInputDialog(this, "Entrez le nouveau site web:", tfWebsite.getText());
        String newSynopsis = JOptionPane.showInputDialog(this, "Entrez le nouveau synopsis:", textArea.getText());

        String updateQuery = "UPDATE regMovie SET director=?, billcast=?, releasedate=?, duration=?, genre=?, rating=?, distributor=?, website=?, synopsis=? WHERE title=?";
        try (PreparedStatement updatePst = conn.prepareStatement(updateQuery)) {
            updatePst.setString(1, newDirector);
            updatePst.setString(2, newBillcast);
            updatePst.setString(3, newReleaseDate);
            updatePst.setString(4, newDuration);
            updatePst.setString(5, newGenre);
            updatePst.setString(6, newRating);
            updatePst.setString(7, newDistributor);
            updatePst.setString(8, newWebsite);
            updatePst.setString(9, newSynopsis);
            updatePst.setString(10, (String) comboBox.getSelectedItem());

            int rowsAffected = updatePst.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Les informations du film ont été mises à jour avec succès.");
            } else {
                JOptionPane.showMessageDialog(this, "Aucune mise à jour effectuée. Vérifiez les données et réessayez.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour des informations du film.");
    }
}

    public void filltime() {
        try {
            String query1 = "SELECT SecTime from secmovie WHERE SecDate=? AND MovieTitle=?";
            PreparedStatement pst1 = conn.prepareStatement(query1);

            pst1.setString(1, st);
            pst1.setString(2, mt);
            ResultSet rs1 = pst1.executeQuery();

            while (rs1.next()) {
                comboBox2.addItem(rs1.getString("SecTime"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
private void deleteMovie() {
    try {
        String selectedMovie = (String) comboBox.getSelectedItem();
        if (selectedMovie != null) {
            // confirmation 
            int confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer le film?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation != JOptionPane.YES_OPTION) {
                System.out.println("Delete cancelled by user.");
                return;
            }

            String deleteQuery = "DELETE FROM regMovie WHERE title=?";
            try (PreparedStatement deletePst = conn.prepareStatement(deleteQuery)) {
                deletePst.setString(1, selectedMovie);
                int rowsAffected = deletePst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Le film a été supprimé avec succès.");
                    comboBox.removeItem(selectedMovie);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de la suppression. Veuillez réessayer.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un film à supprimer");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void clearFields() {
        tfDirector.setText("");
        tfCast.setText("");
        tfReleaseDate.setText("");
        tfDuration.setText("");
        tfGenre.setText("");
        tfRating.setText("");
        tfDistributor.setText("");
        tfWebsite.setText("");
        textArea.setText("");
        comboBox1.removeAllItems();
        comboBox2.removeAllItems();
    }

    public MovieList() {
        setTitle("Liste de films");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(150, 150, 850, 500);
        setBackground(Color.yellow);

        conn = MySQLConnection.dbConnector();

        btnUpdate = new JButton("Mettre à jour");
        btnUpdate.setBackground(new Color(50, 150, 50)); 
        btnUpdate.setForeground(Color.BLUE);
        btnUpdate.setFont(new Font("Serif", Font.BOLD, 16)); 
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateMovieInfo();
            }
        });
        
        btnDelete = new JButton("Supprimer");
        btnDelete.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent arg0) {
        deleteMovie();
    }
});
btnDelete.setBackground(new Color(250, 0, 0));
btnDelete.setForeground(Color.WHITE);
btnDelete.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel lblMovie = new JLabel("Film");
        lblMovie.setForeground(new java.awt.Color(255, 0, 0));
        lblMovie.setFont(new java.awt.Font("Segoe UI", 1, 18));
        comboBox = new JComboBox<String>();
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "SELECT * FROM regMovie WHERE title=? ";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, (String) comboBox.getSelectedItem());

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        mt = rs.getString("title");
                        tfDirector.setText(rs.getString("director"));
                        tfCast.setText(rs.getString("billcast"));
                        tfReleaseDate.setText(rs.getString("releasedate"));
                        tfDuration.setText(rs.getString("duration"));
                        tfGenre.setText(rs.getString("genre"));
                        tfRating.setText(rs.getString("rating"));
                        tfDistributor.setText(rs.getString("distributor"));
                        tfWebsite.setText(rs.getString("website"));
                        textArea.setText(rs.getString("synopsis"));
                        comboBox1.removeAllItems();

                        filldate();
                    }

                    rs.close();
                    pst.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        comboBox.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent arg0) {
        try {
            String query = "SELECT * FROM regMovie WHERE title=? ";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, (String) comboBox.getSelectedItem());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                mt = rs.getString("title");
                tfDirector.setText(rs.getString("director"));
                tfCast.setText(rs.getString("billcast"));
                tfReleaseDate.setText(rs.getString("releasedate"));
                tfDuration.setText(rs.getString("duration"));
                tfGenre.setText(rs.getString("genre"));
                tfRating.setText(rs.getString("rating"));
                tfDistributor.setText(rs.getString("distributor"));
                tfWebsite.setText(rs.getString("website"));

                // Remplissez le textArea avec les anciennes valeurs
                String oldSynopsis = rs.getString("synopsis");
                textArea.setText(oldSynopsis);

                comboBox1.removeAllItems();

                filldate();
            } else {
                // Réinitialiser les champs si le film n'est pas trouvé
                tfDirector.setText("");
                tfCast.setText("");
                tfReleaseDate.setText("");
                tfDuration.setText("");
                tfGenre.setText("");
                tfRating.setText("");
                tfDistributor.setText("");
                tfWebsite.setText("");
                textArea.setText("");
            }

            rs.close();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
});

        JLabel lblCast = new JLabel("Acteur");
        tfCast = new JTextField();
        tfCast.setEditable(false);
        tfCast.setColumns(10);
        lblCast.setForeground(new java.awt.Color(255, 0, 0));
        lblCast.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblDirector = new JLabel("Directeur");
        tfDirector = new JTextField();
        tfDirector.setEditable(false);
        tfDirector.setColumns(10);
        lblDirector.setForeground(new java.awt.Color(255, 0, 0));
        lblDirector.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblReleaseDate = new JLabel("Date de sortie");
        tfReleaseDate = new JTextField();
        tfReleaseDate.setEditable(false);
        tfReleaseDate.setColumns(10);
        lblReleaseDate.setForeground(new java.awt.Color(255, 0, 0));
        lblReleaseDate.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblDuration = new JLabel("Durée");
        tfDuration = new JTextField();
        tfDuration.setEditable(false);
        tfDuration.setColumns(10);
        lblDuration.setForeground(new java.awt.Color(255, 0, 0));
        lblDuration.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblGenre = new JLabel("Genre");
        tfGenre = new JTextField();
        tfGenre.setEditable(false);
        tfGenre.setColumns(10);
        lblGenre.setForeground(new java.awt.Color(255, 0, 0));
        lblGenre.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblRating = new JLabel("Notation");
        tfRating = new JTextField();
        tfRating.setEditable(false);
        tfRating.setColumns(10);
        lblRating.setForeground(new java.awt.Color(255, 0, 0));
        lblRating.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblDistributor = new JLabel("Distributeur");
        tfDistributor = new JTextField();
        tfDistributor.setEditable(false);
        tfDistributor.setColumns(10);
        lblDistributor.setForeground(new java.awt.Color(255, 0, 0));
        lblDistributor.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblWebsite = new JLabel("Site internet");
        tfWebsite = new JTextField();
        tfWebsite.setEditable(false);
        tfWebsite.setColumns(10);
        lblWebsite.setForeground(new java.awt.Color(255, 0, 0));
        lblWebsite.setFont(new java.awt.Font("Segoe UI", 1, 18));

        JLabel lblSynopsis = new JLabel("Synopsis");
        lblSynopsis.setForeground(new java.awt.Color(255, 0, 0));
        lblSynopsis.setFont(new java.awt.Font("Segoe UI", 1, 18));
        tfCast.setBackground(new java.awt.Color(255, 255, 0));
        tfDirector.setBackground(new java.awt.Color(255, 255, 0));
        tfReleaseDate.setBackground(new java.awt.Color(255, 255, 0));
        tfDuration.setBackground(new java.awt.Color(255, 255, 0));
        tfGenre.setBackground(new java.awt.Color(255, 255, 0));
        tfRating.setBackground(new java.awt.Color(255, 255, 0));
        tfDistributor.setBackground(new java.awt.Color(255, 255, 0));
        tfWebsite.setBackground(new java.awt.Color(255, 255, 0));

        JScrollPane scrollPane = new JScrollPane();

        JLabel lblNextSessionDate = new JLabel("Horaires des sessions");
        lblNextSessionDate.setForeground(new java.awt.Color(255, 0, 0));
        lblNextSessionDate.setFont(new java.awt.Font("Segoe UI", 1, 18));

        comboBox1 = new JComboBox<String>();
        comboBox2 = new JComboBox<String>();

        GroupLayout groupLayout_1 = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout_1);

        groupLayout_1.setHorizontalGroup(
            groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout_1.createSequentialGroup()
                            .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblDirector)
                                .addComponent(lblWebsite)
                                .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblRating)
                                    .addComponent(lblDuration))
                                .addGroup(groupLayout_1.createSequentialGroup()
                                    .addGap(8)
                                    .addComponent(lblMovie)))
                            .addGap(18)
                            .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(comboBox, 0, 192, Short.MAX_VALUE)
                                .addComponent(tfRating, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addComponent(tfWebsite, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfDuration, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addComponent(tfDirector, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblCast)
                                .addComponent(lblReleaseDate)
                                .addComponent(lblGenre)
                                .addComponent(lblDistributor)
                                .addComponent(lblNextSessionDate))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(groupLayout_1.createSequentialGroup()
                                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfDistributor)
                                    .addComponent(tfGenre)
                                    .addComponent(tfReleaseDate)
                                    .addComponent(tfCast, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))))
                        .addGroup(groupLayout_1.createSequentialGroup()
                            .addComponent(lblSynopsis)
                            .addGap(18)
                            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)))
                    .addGap(49))
                .addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                
        );

        groupLayout_1.setVerticalGroup(
            groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMovie)
                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCast)
                        .addComponent(tfCast, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDirector)
                        .addComponent(lblReleaseDate)
                        .addComponent(tfReleaseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfDirector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDuration)
                        .addComponent(tfDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGenre)
                        .addComponent(tfGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRating)
                        .addComponent(tfRating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDistributor)
                        .addComponent(tfDistributor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblWebsite)
                        .addComponent(tfWebsite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNextSessionDate)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblSynopsis)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                       
                    .addContainerGap(91, Short.MAX_VALUE))
        );

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        scrollPane.setViewportView(textArea);

        filltitle();
        getContentPane().setBackground(new Color(209, 255, 255));
    }
}

