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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pojo.Category;


public class CategoryServices {
    ObservableList o = FXCollections.observableArrayList();
    public static List<Category> getCategory(String kw) throws SQLException {
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM category";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            Category c = new Category(rs.getString("id"), rs.getString("name"), rs.getString("description"));
            categories.add(c);
        }
        return categories;
    }
    
    public static boolean addCategory(String name, String desc) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT category " + "SET id=?, name=?, description=?";

            PreparedStatement stm = conn.prepareStatement(sql);   
            
            String id = UUID.randomUUID().toString();
            
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, desc);
            
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
    
    public static boolean updateCategory(String name, String desc, String id_category) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "UPDATE category " + "SET name=?, description=? WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, name);
            stm.setString(2, desc);
            stm.setString(3, id_category);
 
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
    
    public static boolean deleteCategory(String category_id) {
        try {
            Connection conn = Utils.getConn();
            String sql = "DELETE FROM category WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, category_id);
            
            return stm.executeUpdate() >= 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }   
}
