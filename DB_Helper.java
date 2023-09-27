
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Helper {

    public static Connection getConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Stock_DB?use Unicode = true & characterEncoding=UTF-8 & serverTimezone = UTC", "root", "");

        } catch (SQLException ex) {
            Logger.getLogger(DB_Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

}
