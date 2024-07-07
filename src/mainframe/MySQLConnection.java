package mainframe;
import java.sql.*;
import javax.swing.*;

public class MySQLConnection {
    Connection conn = null;

    public static Connection dbConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet?zeroDateTimeBehavior=convertToNull", "root", "");

            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}

