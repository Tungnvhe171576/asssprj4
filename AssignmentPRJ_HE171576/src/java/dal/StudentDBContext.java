/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.Course;
import model.Exam;
import model.Student;
import model.Subject;

/**
 *
 * @author sonnt-local
 */
public class StudentDBContext extends DBContext<Student> {

//    public Student getStudentByUsernamePassword(String username, String password) {
//    PreparedStatement stm = null;
//    Student student = null;
//    try {
//        String sql = "SELECT u.username, u.displayname, s.sid, s.sname, c.cid, c.cname " +
//                     "FROM users u " +
//                     "LEFT JOIN students s ON s.username = u.username " +
//                     "LEFT JOIN students_courses sc ON sc.sid = s.sid " +
//                     "LEFT JOIN courses c ON sc.cid = c.cid " +
//                     "INNER JOIN semester sem ON sem.semid = c.semid " +
//                     "WHERE u.username = ? AND u.[password] = ? AND sem.active = 1";
//        stm = connection.prepareStatement(sql);
//        stm.setString(1, username);
//        stm.setString(2, password);
//        ResultSet rs = stm.executeQuery();
//        if (rs.next()) {
//            student = new Student();
//            student.setId(rs.getInt("sid"));
//            student.setName(rs.getString("sname"));
//
//            do {
//                int cid = rs.getInt("cid");
//                if (cid != 0) {
//                    Course course = new Course();
//                    course.setId(cid);
//                    course.setName(rs.getString("cname"));
//                 
//                }
//            } while (rs.next());
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//    } finally {
//        try {
//            if (stm != null) {
//                stm.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    return student;
//}
     public Student getStudentByUsername(String username) {
        PreparedStatement stm = null;
        Student student = null;
        try {
            String sql = "SELECT s.sid, s.sname \n"
                    + "FROM students s \n"
                    + "INNER JOIN users_students us ON us.sid = s.sid \n"
                    + "WHERE us.username = ? AND us.active = 1";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("sid"));
                student.setName(rs.getString("sname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return student;
    }


    public ArrayList<Student> getStudentsByCourse(int cid) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT s.sid,s.sname FROM students s INNER JOIN students_courses sc ON s.sid = sc.sid\n"
                    + "						INNER JOIN courses c ON c.cid = sc.cid\n"
                    + "						WHERE c.cid = ?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                students.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Student getStudentByUserId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
