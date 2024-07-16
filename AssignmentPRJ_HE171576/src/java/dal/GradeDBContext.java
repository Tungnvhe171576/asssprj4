/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.Course;
import model.Exam;
import model.Grade;
import model.Student;
import model.Subject;

public class GradeDBContext extends DBContext<Grade> {

    public ArrayList<Grade> getGradesFromExamIds(ArrayList<Integer> eids) {
        ArrayList<Grade> grades = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT eid,sid,score FROM grades WHERE (1>2)";
            for (Integer eid : eids) {
                sql += " OR eid = ?";
            }

            stm = connection.prepareStatement(sql);

            for (int i = 0; i < eids.size(); i++) {
                stm.setInt((i + 1), eids.get(i));
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Grade g = new Grade();
                g.setScore(rs.getFloat("score"));

                Student s = new Student();
                s.setId(rs.getInt("sid"));
                g.setStudent(s);

                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                g.setExam(e);

                grades.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }

    public void insertGradesForCourse(int cid, ArrayList<Grade> grades) {
        String sql_remove = "DELETE grades WHERE sid IN (SELECT sid FROM students_courses WHERE cid = ?)";
        String sql_insert = "INSERT INTO [grades]\n"
                + "           ([eid]\n"
                + "           ,[sid]\n"
                + "           ,[score])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        
        PreparedStatement stm_remove =null;
        ArrayList<PreparedStatement> stm_inserts = new ArrayList<>();
        
        try {
            connection.setAutoCommit(false);
            stm_remove = connection.prepareStatement(sql_remove);
            stm_remove.setInt(1, cid);
            stm_remove.executeUpdate();
            
            for (Grade grade : grades) {
                PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
                stm_insert.setInt(1, grade.getExam().getId());
                stm_insert.setInt(2,grade.getStudent().getId());
                stm_insert.setFloat(3, grade.getScore());
                stm_insert.executeUpdate();
                stm_inserts.add(stm_insert);
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
                stm_remove.close();
                for (PreparedStatement stm_insert : stm_inserts) {
                    stm_insert.close();
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    

  public ArrayList<Grade> getGradesByStudentID(int stdID) {
        ArrayList<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.eid, g.sid, g.score, "
                   + "e.eid, e.duration, e.[from], "
                   + "a.aid, a.aname, a.weight, "
                   + "sub.subid, sub.subname, "
                   + "c.cid, c.cname "
                   + "FROM grades g "
                   + "JOIN exams e ON g.eid = e.eid "
                   + "JOIN assesments a ON e.aid = a.aid "
                   + "JOIN subjects sub ON a.subid = sub.subid "
                   + "JOIN courses c ON sub.subid = c.subid "
                   + "WHERE g.sid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, stdID);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Grade g = new Grade();
                    g.setScore(rs.getFloat("score"));

                    Exam e = new Exam();
                    e.setId(rs.getInt("eid"));
                    e.setDuration(rs.getInt("duration"));
                    e.setFrom(rs.getDate("from"));

                    Assessment a = new Assessment();
                    a.setId(rs.getInt("aid"));
                    a.setName(rs.getString("aname"));
                    a.setWeight(rs.getInt("weight"));

                    e.setAssessment(a);
                    g.setExam(e);

                    Subject sub = new Subject();
                    sub.setId(rs.getInt("subid"));
                    sub.setName(rs.getString("subname"));

                    Course c = new Course();
                    c.setId(rs.getInt("cid"));
                    c.setName(rs.getString("cname"));
                    c.setSubject(sub);

                    g.setCourse(c);

                    grades.add(g);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return grades;
    }

    
    public ArrayList<Grade> getGradesByCourseAndStudent(int courseID, int studentID) {
    ArrayList<Grade> grades = new ArrayList<>();
    String sql = "SELECT g.score, a.aname, a.weight "
               + "FROM grades g "
               + "JOIN exams e ON g.eid = e.eid "
               + "JOIN assesments a ON e.aid = a.aid "
               + "JOIN courses c ON a.subid = c.subid "
               + "WHERE c.cid = ? AND g.sid = ?";

    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setInt(1, courseID);
        stm.setInt(2, studentID);
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Grade g = new Grade();
                g.setScore(rs.getFloat("score"));

                Assessment a = new Assessment();
                a.setName(rs.getString("aname"));
                a.setWeight(rs.getFloat("weight"));

                Exam e = new Exam();
                e.setAssessment(a);
                g.setExam(e);

                grades.add(g);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }

    return grades;
}


    @Override
    public void insert(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Grade get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Grade> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Grade> getGradesByStudentId(int studentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
