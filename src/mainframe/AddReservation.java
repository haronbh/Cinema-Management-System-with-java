/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class AddReservation extends JFrame {

    private JTextField movieIdTextField;
    private JTextField sessionMovieIdTextField;
    private JTextField clientNameTextField;
    private JButton addButton;
    private Connection connection;

    public AddReservation() {
        setTitle("Ajouter une réservation");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setLayout();
        addListeners();
    }

    private void initComponents() {
        movieIdTextField = createTextField();
        sessionMovieIdTextField = createTextField();
        clientNameTextField = createTextField();
        addButton = new JButton("Ajouter");

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font btnFont = new Font("Arial", Font.BOLD, 16);

        movieIdTextField.setFont(labelFont);
        sessionMovieIdTextField.setFont(labelFont);
        clientNameTextField.setFont(labelFont);
        addButton.setFont(btnFont);

        movieIdTextField.setPreferredSize(new Dimension(200, 30));
        sessionMovieIdTextField.setPreferredSize(new Dimension(200, 30));
        clientNameTextField.setPreferredSize(new Dimension(200, 30));

        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.setForeground(Color.red);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return textField;
    }

    private void setLayout() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        inputPanel.add(createLabel("Identificateur du film :"));
        inputPanel.add(movieIdTextField);
        inputPanel.add(createLabel("Identificateur de la session :"));
        inputPanel.add(sessionMovieIdTextField);
        inputPanel.add(createLabel("Nom du client :"));
        inputPanel.add(clientNameTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(233, 132, 245));  
        buttonPanel.add(addButton);

        inputPanel.setBackground(new Color(140, 207, 157));

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(0, 0, 128));  // Bleu marine
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    connection = MySQLConnection.dbConnector();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(new java.util.Date());

                    String query = "INSERT INTO reserv (regmovie_id, secmovie_id, date, name) VALUES (?, ?, ?, ?)";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setInt(1, Integer.parseInt(movieIdTextField.getText()));
                    pst.setInt(2, Integer.parseInt(sessionMovieIdTextField.getText()));
                    pst.setDate(3, Date.valueOf(LocalDate.parse(formattedDate)));
                    pst.setString(4, clientNameTextField.getText());

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Réservation enregistrée avec succès");

                    movieIdTextField.setText("");
                    sessionMovieIdTextField.setText("");
                    clientNameTextField.setText("");

                    pst.close();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                AddReservation frame = new AddReservation();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}