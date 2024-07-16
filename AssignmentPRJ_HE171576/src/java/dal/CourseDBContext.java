/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Course;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author X1 Nano
 */
public class CourseDBContext extends DBContext<Course> {

//    public static void main(String[] args)
//    {
//        CourseDBContext db = new CourseDBContext();
//        ArrayList<Course> coursesByLecturer = db.getCoursesByLecturer(1);
//        System.out.println(coursesByLecturer.size());
//    }
    
    public ArrayList<Course> getCoursesByLecturer(int lid) {
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT c.cid,c.cname FROM courses c INNER JOIN lecturers l ON l.lid = c.lid\n"
                    + "				INNER JOIN semester sem ON sem.semid = c.semid\n"
                    + "				WHERE l.lid = ? AND sem.active = 1";
            
            
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses; 
        
        
    }
// public ArrayList<Course> getCoursesByStudent(int sid) {
//        ArrayList<Course> courses = new ArrayList<>();
//        PreparedStatement stm = null;
//        try {
//            String sql = "SELECT c.cid, c.cname FROM courses c " +
//                         "INNER JOIN students_courses sc ON sc.cid = c.cid " +
//                         "INNER JOIN semester sem ON sem.semid = c.semid " +
//                         "WHERE sc.sid = ? AND sem.active = 1";
//
//            stm = connection.prepareStatement(sql);
//            stm.setInt(1, sid);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Course c = new Course();
//                c.setId(rs.getInt("cid"));
//                c.setName(rs.getString("cname"));
//                courses.add(c);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (stm != null) stm.close();
//                if (connection != null) connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return courses;
//    }
     public ArrayList<Course> getCoursesByStudent(int sid) {
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT c.cid, c.cname FROM courses c " +
                         "INNER JOIN students_courses sc ON sc.cid = c.cid " +
                         "INNER JOIN semester sem ON sem.semid = c.semid " +
                         "WHERE sc.sid = ? AND sem.active = 1";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }

    @Override
    public void insert(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Course> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
