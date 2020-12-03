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
import pojo.PublishingCompany;

public class PublishingCompanyServices {
    public static List<PublishingCompany> getPublishingCompany(String kw) throws SQLException {
        Connection conn = Utils.getConn();
        String sql = "SELECT * FROM publishing_company";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        List<PublishingCompany> pcs = new ArrayList<>();
        while (rs.next()) {
            PublishingCompany a = new PublishingCompany(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
            pcs.add(a);
        }
        return pcs;
    }
    
    public static boolean addPublishingCompany(String name, String phone, String address, String desc) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT publishing_company " + "SET id=?, name=?, phone=?, address=?, description=?";

            PreparedStatement stm = conn.prepareStatement(sql);   
            
            String id = UUID.randomUUID().toString();
            
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, desc);

            stm.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            ex.getStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PublishingCompanyServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }
    
    public static boolean updatePublishingCompany(String name, String phone, String address, String desc, String pc_id) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "UPDATE publishing_company " + " SET name=?, phone=?, address=?, description=? WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
           
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setString(3, address);
            stm.setString(4, desc);
            stm.setString(5, pc_id);
 
            stm.executeUpdate();
            conn.commit();         
        } catch (SQLException ex) {
            ex.getStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PublishingCompanyServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }
    
    public static boolean deletePublishingCompany(String pc_id) {
        try {
            Connection conn = Utils.getConn();
            String sql = "DELETE FROM publishing_company WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, pc_id);
            
            return stm.executeUpdate() >= 0;
        } catch (SQLException ex) {
            Logger.getLogger(PublishingCompanyServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
