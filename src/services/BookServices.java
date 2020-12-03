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
import pojo.Book;

/**
 *
 * @author ngtro_f237v2a
 */
public class BookServices {
    public static List<Book> getBook(String kw) throws SQLException {
        Connection conn = Utils.getConn();
        String sql = "SELECT b.id, b.name, a.nickname, c.name, p.name, b.publishing_year, b.entry_date, b.location, b.description\n" +
                        "FROM book b, author a, category c, publishing_company p \n" +
                        "WHERE (b.author_id = a.id AND b.category_id = c.id) AND (b.publishing_company_id = p.id) ";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book(rs.getString("b.id"), rs.getString("b.name"), rs.getString("a.nickname"), rs.getString("c.name"),rs.getString("p.name"),rs.getString("b.publishing_year"),rs.getString("b.entry_date"),rs.getString("b.location"),rs.getString("b.description"));
            books.add(b);
        }
        return books;
    }
    
    public static boolean addBook(String name, String author_id, String category_id, String pc_id, String pc_year, String entry_date, String location, String desc) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT book " + "SET id=?, name=?, author_id=?, category_id=?, publishing_company_id=?, publishing_year=?, entry_date=?, location=?, description=?";
            PreparedStatement stm = conn.prepareStatement(sql);      
            String id = UUID.randomUUID().toString();
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, author_id);
            stm.setString(4, category_id);
            stm.setString(5, pc_id);
            stm.setString(6, pc_year);
            stm.setString(7, entry_date);
            stm.setString(8, location);
            stm.setString(9, desc);

            stm.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            ex.getStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }
    
    public static boolean updateBook(String name, String author_id, String category_id, String pc_id, String pc_year, String entry_date, String location, String desc, String id_book) {
        Connection conn = Utils.getConn();
        try {
            conn.setAutoCommit(false);
            String sql = "UPDATE book " + "SET name=?, author_id=?, category_id=?, publishing_company_id=?, publishing_year=?, entry_date=?, location=?, description=? WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            

            stm.setString(1, name);
            stm.setString(2, author_id);
            stm.setString(3, category_id);
            stm.setString(4, pc_id);
            stm.setString(5, pc_year);
            stm.setString(6, entry_date);
            stm.setString(7, location);
            stm.setString(8, desc);
            stm.setString(9, id_book);
 
            stm.executeUpdate();
            conn.commit();         
        } catch (SQLException ex) {
            ex.getStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
         return false;
    }
    
    public static boolean deleteBook(String book_id) {
        try {
            Connection conn = Utils.getConn();
            String sql = "DELETE FROM book WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, book_id);
            
            return stm.executeUpdate() >= 0;
        } catch (SQLException ex) {
            Logger.getLogger(BookServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }    
}
