/////*
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//// */
////package dal;
////
////import java.util.ArrayList;
////import model.Lecturer;
////import model.User;
////
////
////import java.sql.*;
////import java.util.logging.Level;
////import java.util.logging.Logger;
////
////
////
/////**
//// *
//// * @author ADMIN
//// */
////public class LecturerDBContext extends DBContext<Lecturer>{
////        public User getUserByUsernamePassword(String username, String password) {
////        PreparedStatement stm =null;
////        User user = null;
////        try {
////            String sql = "SELECT u.username,u.displayname,l.lid,l.lname\n"
////                    + "FROM users u LEFT JOIN users_lecturers ul ON ul.username = u.username AND ul.active = 1\n"
////                    + "						LEFT JOIN lecturers l ON ul.lid = l.lid\n"
////                    + "						WHERE u.username = ? AND u.[password] = ?";
////            stm = connection.prepareStatement(sql);
////            stm.setString(1, username);
////            stm.setString(2, password);
////            ResultSet rs = stm.executeQuery();
////            if(rs.next())
////            {
////                user = new User();
////                user.setDisplayname(rs.getString("displayname"));
////                user.setUsername(username);
////                int lid = rs.getInt("lid");
////                if(lid!=0)
////                {
////                   Lecturer lecturer = new Lecturer();
////                   lecturer.setId(lid);
////                   lecturer.setName(rs.getString("lname"));
////                   user.setLecturer(lecturer);
////                }
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        finally
////        {
////            try {
////                stm.close();
////                connection.close();
////            } catch (SQLException ex) {
////                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////        return user;
////    }
////
////        
////    @Override
////    public void insert(Lecturer model) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    @Override
////    public void update(Lecturer model) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    @Override
////    public void delete(Lecturer model) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    @Override
////    public Lecturer get(int id) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    @Override
////    public ArrayList<Lecturer> list() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////}
//package dal;
//
//import model.Lecturer;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class LecturerDBContext extends DBContext<Lecturer> {
//
//    public Lecturer getLecturerByUsername(String username) {
//        PreparedStatement stm = null;
//        Lecturer lecturer = null;
//        try {
//            String sql = "SELECT l.lid, l.lname \n"
//                    + "FROM lecturers l \n"
//                    + "INNER JOIN users_lecturers ul ON ul.lid = l.lid \n"
//                    + "WHERE ul.username = ? AND ul.active = 1";
//// String sql = "SELECT u.username,u.displayname,l.lid,l.lname\n"
////                   + "FROM users u LEFT JOIN users_lecturers ul ON ul.username = u.username AND ul.active = 1\n"
////                    + "						LEFT JOIN lecturers l ON ul.lid = l.lid\n"
////                 + "						WHERE u.username = ? AND u.[password] = ?";
//            stm = connection.prepareStatement(sql);
//            stm.setString(1, username);
//            
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                lecturer = new Lecturer();
//                lecturer.setId(rs.getInt("lid"));
//                lecturer.setName(rs.getString("lname"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (stm != null) stm.close();
//                if (connection != null) connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return lecturer;
//    }
//
//    @Override
//    public void insert(Lecturer model) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void update(Lecturer model) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void delete(Lecturer model) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Lecturer get(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ArrayList<Lecturer> list() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
package dal;

import model.Lecturer;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturerDBContext extends DBContext<Lecturer> {

    public Lecturer getLecturerByUsername(String username) {
        PreparedStatement stm = null;
        Lecturer lecturer = null;
        try {
            String sql = "SELECT l.lid, l.lname \n"
                    + "FROM lecturers l \n"
                    + "INNER JOIN users_lecturers ul ON ul.lid = l.lid \n"
                    + "WHERE ul.username = ? AND ul.active = 1";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                lecturer = new Lecturer();
                lecturer.setId(rs.getInt("lid"));
                lecturer.setName(rs.getString("lname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecturer;
    }

    @Override
    public void insert(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Lecturer get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Lecturer> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
