/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Author;

public class AuthorServices {
    public static List<Author> getAuthor(String kw) throws SQLException {
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM author";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        List<Author> authors = new ArrayList<>();
        while (rs.next()) {
            Author a = new Author(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
            authors.add(a);
        }
        return authors;
    }
    
    public static boolean addAuthor(String nickname, String name, String birthday, String gender, String city, String country, String background, String description) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT author " + "SET id=?, nickname=?, name=?, birthday=?, gender=?, city=?, country=?, background=?,description=?";

            PreparedStatement stm = conn.prepareStatement(sql);   
            
            String id = UUID.randomUUID().toString();
            
            stm.setString(1, id);
            stm.setString(2, nickname);
            stm.setString(3, name);
            stm.setString(4, birthday);
            stm.setString(5, gender);
            stm.setString(6, city);
            stm.setString(7, country);
            stm.setString(8, background);
            stm.setString(9, description);


            stm.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            ex.getStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CategoryServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }
    
    public static boolean updateAuthor(String nickname, String name, String birthday, String gender, String city, String country, String background, String description, String id_author) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "UPDATE author " + "SET nickname=?, name=?, birthday=?, gender=?, city=?, country=?, background=?, description=? WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, nickname);
            stm.setString(2, name);
            stm.setString(3, birthday);
            stm.setString(4, gender);
            stm.setString(5, city);
            stm.setString(6, country);
            stm.setString(7, background);
            stm.setString(8, description);
            stm.setString(9, id_author);
 
            stm.executeUpdate();
            conn.commit();         
        } catch (SQLException ex) {
            ex.getStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AuthorServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
         return false;
    }
    
    public static boolean deleteAuthor(String author_id) {
        try {
            Connection conn = Utils.getConn();
            String sql = "DELETE FROM author WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, author_id);
            
            return stm.executeUpdate() >= 0;
        } catch (SQLException ex) {
            Logger.getLogger(AuthorServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Author[] getAuthor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
