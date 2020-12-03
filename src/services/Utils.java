/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author ngtro_f237v2a
 */
public class Utils {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "123456";
    private static Connection conn;
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Alert getAlert(String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        
        return alert;
    }
    /**
     * @return the conn
     */
    public static Connection getConn() {
        return conn;
    }
}
