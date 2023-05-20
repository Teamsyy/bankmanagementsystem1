
package bankapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uSeR
 */
public class BankingConnection {
    public static Connection connect(){
        String URL = "jdbc:mysql://127.0.0.1:3306/mydb1";
        String username = "root";
        String password = "Teamsy2346";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankingConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BankingConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
}
