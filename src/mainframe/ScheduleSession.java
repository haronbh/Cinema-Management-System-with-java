package mainframe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;

import javax.swing.ImageIcon;
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
import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;


public class ScheduleSession extends JFrame {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleSession frame = new ScheduleSession();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	private JTextField tfScheduleDate;
	private JTextField tfScheduleTime;
	private JComboBox comboBox;
	private String tit;
	
public void filltitle() {
    try {
        String query = "SELECT title from regMovie";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            comboBox.addItem(rs.getString("title"));
        }

        pst.close();
        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private int getMovieId(String movieTitle) {
    try {
        String query = "SELECT id FROM regMovie WHERE title=?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, movieTitle);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int movieId = rs.getInt("id");
            System.out.println("trouvé '" + movieTitle + "': " + movieId);
            return movieId;
        } else {
            System.out.println("non trouvé '" + movieTitle + "'");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Retourne -1 si l'ID n'est pas trouvé
}
	
	public ScheduleSession() {
                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                conn = MySQLConnection.dbConnector();
		setTitle("Planifier une session");
		setBounds(100, 100, 609, 493);
		
		JLabel lblMovieTitle = new JLabel("Titre du film");
		lblMovieTitle.setForeground(Color.blue);
                lblMovieTitle.setFont(new Font("Serif", Font.BOLD, 16));
		JLabel lblScheduleDate = new JLabel("Date prévue");
		lblScheduleDate.setForeground(Color.blue);
                lblScheduleDate.setFont(new Font("Serif", Font.BOLD, 16));
		JLabel lblScheduleTime = new JLabel("emploi du temps");
		lblScheduleTime.setForeground(Color.blue);
                lblScheduleTime.setFont(new Font("Serif", Font.BOLD, 16));
		tfScheduleDate = new JTextField();
		tfScheduleDate.setToolTipText("DD/MM/YYYY");
		tfScheduleDate.setColumns(10);
		
		tfScheduleTime = new JTextField();
		tfScheduleTime.setToolTipText("hh:mm");
		tfScheduleTime.setColumns(10);
		
		
		JLabel lblScheduleMoiveSession = new JLabel("Planifier une session");
		lblScheduleMoiveSession.setForeground(Color.red);
                lblScheduleMoiveSession.setFont(new Font("Serif", Font.BOLD, 26));
		
		JButton btnSchedule = new JButton("Planifier");
                        btnSchedule.setBackground(new java.awt.Color(0, 0, 255));
                        btnSchedule.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
                        btnSchedule.setForeground(new java.awt.Color(255, 255, 255));
btnSchedule.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent arg0) {
        try {
            // Obtenez l'ID du film correspondant au titre sélectionné
            String selectedTitle = (String) comboBox.getSelectedItem();
            int movieId = getMovieId(selectedTitle);

            // Vérifiez si l'ID du film est valide
            if (movieId != -1) {
                // Insérez les données dans la table secMovie avec l'ID du film
                String query1 = "INSERT INTO secMovie (MovieId, MovieTitle, SecDate, SecTime) VALUES (?, ?, ?, ?)";
                PreparedStatement pst1 = conn.prepareStatement(query1);

                pst1.setInt(1, movieId);
                pst1.setString(2, selectedTitle);
                pst1.setString(3, tfScheduleDate.getText());
                pst1.setString(4, tfScheduleTime.getText());

                pst1.execute();
                JOptionPane.showMessageDialog(null, "Film programmé avec succès");

                tfScheduleDate.setText("");
                tfScheduleTime.setText("");

                pst1.close();
            } else {
                JOptionPane.showMessageDialog(null, "Impossible de trouver l'ID du film pour le titre sélectionné.");
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
    }
});
		
		 comboBox = new JComboBox();
		 
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(274)
									.addGap(43))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(35)
									.addComponent(lblScheduleMoiveSession, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
									.addGap(78))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblMovieTitle)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblScheduleTime)
									.addComponent(lblScheduleDate)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tfScheduleTime, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(tfScheduleDate, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(comboBox, 0, 444, Short.MAX_VALUE)
								.addComponent(btnSchedule, Alignment.TRAILING))
							.addGap(42)))))));
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addGap(11)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblScheduleMoiveSession)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMovieTitle)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblScheduleDate)
						.addComponent(tfScheduleDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblScheduleTime)
						.addComponent(tfScheduleTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSchedule)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
                                getContentPane().setBackground(new Color(235, 250, 190));
		filltitle();

	}
}
