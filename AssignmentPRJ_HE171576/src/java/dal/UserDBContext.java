///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dal;
//
//import java.util.ArrayList;
//import model.User;
//import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import model.Course;
//import model.Lecturer;
//import model.Student;
//
///**
// *
// * @author sonnt-local
// */
//public class UserDBContext extends DBContext<User> {
//
//    public User getUserByUsernamePassword(String username, String password) {
//        PreparedStatement stm =null;
//        User user = null;
//        try {
//            String sql = "SELECT u.username,u.displayname,l.lid,l.lname\n"
//                    + "FROM users u LEFT JOIN users_lecturers ul ON ul.username = u.username AND ul.active = 1\n"
//                    + "						LEFT JOIN lecturers l ON ul.lid = l.lid\n"
//                    + "						WHERE u.username = ? AND u.[password] = ?";
//            stm = connection.prepareStatement(sql);
//            stm.setString(1, username);
//            stm.setString(2, password);
//            ResultSet rs = stm.executeQuery();
//            if(rs.next())
//            {
//                user = new User();
//                user.setDisplayname(rs.getString("displayname"));
//                user.setUsername(username);
//                int lid = rs.getInt("lid");
//                if(lid!=0)
//                {
//                   Lecturer lecturer = new Lecturer();
//                   lecturer.setId(lid);
//                   lecturer.setName(rs.getString("lname"));
//                   user.setLecturer(lecturer);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally
//        {
//            try {
//                stm.close();
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return user;
//    }
//
//    
////        public Student getStudentByUsernamePassword(String username, String password) {
////    PreparedStatement stm = null;
////    Student student = null;
////    try {
////        String sql = "SELECT u.username, u.displayname, s.sid, s.sname, c.cid, c.cname " +
////                     "FROM users u " +
////                     "LEFT JOIN students s ON s.username = u.username " +
////                     "LEFT JOIN students_courses sc ON sc.sid = s.sid " +
////                     "LEFT JOIN courses c ON sc.cid = c.cid " +
////                     "INNER JOIN semester sem ON sem.semid = c.semid " +
////                     "WHERE u.username = ? AND u.[password] = ? AND sem.active = 1";
////        stm = connection.prepareStatement(sql);
////        stm.setString(1, username);
////        stm.setString(2, password);
////        ResultSet rs = stm.executeQuery();
////        if (rs.next()) {
////            student = new Student();
////            student.setId(rs.getInt("sid"));
////            student.setName(rs.getString("sname"));
////
////            do {
////                int cid = rs.getInt("cid");
////                if (cid != 0) {
////                    Course course = new Course();
////                    course.setId(cid);
////                    course.setName(rs.getString("cname"));
////                 
////                }
////            } while (rs.next());
////        }
////    } catch (SQLException ex) {
////        Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
////    } finally {
////        try {
////            if (stm != null) {
////                stm.close();
////            }
////            if (connection != null) {
////                connection.close();
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
////    return student;
////}
//  public User getUserByUsernamePassword(String username, String password) {
//        PreparedStatement stm = null;
//        User user = null;
//        try {
//            String sql = "SELECT u.username, u.displayname, " +
//                         "l.lid, l.lname, " +
//                         "s.sid, s.sname " +
//                         "FROM users u " +
//                         "LEFT JOIN users_lecturers ul ON ul.username = u.username AND ul.active = 1 " +
//                         "LEFT JOIN lecturers l ON ul.lid = l.lid " +
//                         "LEFT JOIN users_students us ON us.username = u.username AND us.active = 1 " +
//                         "LEFT JOIN students s ON us.sid = s.sid " +
//                         "WHERE u.username = ? AND u.[password] = ?";
//            stm = connection.prepareStatement(sql);
//            stm.setString(1, username);
//            stm.setString(2, password);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                user = new User();
//                user.setDisplayname(rs.getString("displayname"));
//                user.setUsername(username);
//
//                int lid = rs.getInt("lid");
//                if (lid != 0) {
//                    Lecturer lecturer = new Lecturer();
//                    lecturer.setId(lid);
//                    lecturer.setName(rs.getString("lname"));
//                    user.setLecturer(lecturer);
//                }
//
//                int sid = rs.getInt("sid");
//                if (sid != 0) {
//                    Student student = new Student();
//                    student.setId(sid);
//                    student.setName(rs.getString("sname"));
//                    user.setStudent(student);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (stm != null) {
//                    stm.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return user;
//  }
//  
//    @Override
//    public void insert(User model) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void update(User model) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void delete(User model) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public User get(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ArrayList<User> list() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//}
package dal;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDBContext extends DBContext<User> {

    public User getUserByUsernamePassword(String username, String password) {
        PreparedStatement stm = null;
        User user = null;
        try {
            String sql = "SELECT username, displayname \n"
                    + "FROM users \n"
                    + "WHERE username = ? AND [password] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(username);
                user.setDisplayname(rs.getString("displayname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
