///////*
////// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
////// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
////// */
//////
//////package controller.exam;
//////
//////import dal.CourseDBContext;
//////import dal.ExamDBContext;
//////import java.io.IOException;
//////import java.io.PrintWriter;
//////import jakarta.servlet.ServletException;
//////import jakarta.servlet.http.HttpServlet;
//////import jakarta.servlet.http.HttpServletRequest;
//////import jakarta.servlet.http.HttpServletResponse;
//////import java.util.ArrayList;
//////import model.Course;
//////import model.Exam;
//////import model.Student;
//////
///////**
////// *
////// * @author ADMIN
////// */
//////public class ViewCourseByStudentController extends HttpServlet {
//////   
//////    protected void doGet(HttpServletRequest request, HttpServletResponse response, Student student)
//////    throws ServletException, IOException {
//////        CourseDBContext db = new CourseDBContext();
//////        int sid = student.getId();
//////        ArrayList<Course> courses = db.getCoursesByStudent(sid);
//////        request.setAttribute("courses", courses);
//////        request.getRequestDispatcher("../view/course/student.jsp").forward(request, response);
//////    } 
//////
//////  
//////    protected void doPost(HttpServletRequest request, HttpServletResponse response, Student student)
//////    throws ServletException, IOException {
//////        int cid = Integer.parseInt(request.getParameter("cid"));
//////        int sid = student.getId();
//////        
//////        ExamDBContext db = new ExamDBContext();
//////        ArrayList<Exam> exams = db.getExamsByCourse(cid);
//////        request.setAttribute("exams", exams);
//////        
//////        request.getRequestDispatcher("../view/exam/student.jsp").forward(request, response);
//////    }
//////
//////    /**
//////     * Returns a short description of the servlet.
//////     * @return a String containing servlet description
//////     */
//////    @Override
//////    public String getServletInfo() {
//////        return "Servlet for students to view their courses and exams";
//////    }
//////}
////package controller.exam;
////
////import controller.auth.BaseRequiredStudentAuthenticationController;
////import dal.CourseDBContext;
////import dal.ExamDBContext;
////import java.io.IOException;
////import java.io.PrintWriter;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServlet;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import java.util.ArrayList;
////import model.Course;
////import model.Exam;
////import model.Student;
////import model.User;
////
////public class ViewCourseByStudentController extends BaseRequiredStudentAuthenticationController {
////
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////    throws ServletException, IOException {
////      
////      Student student = (Student) request.getSession().getAttribute("student");
////
////        CourseDBContext db = new CourseDBContext();
////        int sid = student.getId();
////        ArrayList<Course> courses = db.getCoursesByStudent(sid);
////        request.setAttribute("courses", courses);
////        request.getRequestDispatcher("../view/course/student.jsp").forward(request, response);
////    }
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////    throws ServletException, IOException {
////        // Retrieve student from session or request attribute
////        Student student = (Student) request.getSession().getAttribute("student");
////        
////
////        int cid = Integer.parseInt(request.getParameter("cid"));
////        int sid = student.getId();
////
////        ExamDBContext db = new ExamDBContext();
////        ArrayList<Exam> exams = db.getExamsByCourse(cid);
////        request.setAttribute("exams", exams);
////
////        request.getRequestDispatcher("../view/exam/student.jsp").forward(request, response);
////    }
//////
//////    @Override
//////    public String getServletInfo() {
//////        return "Servlet for students to view their courses and exams";
//////    }
//////}
//////   @Override
//////   protected void doGet(HttpServletRequest request, HttpServletResponse response)
//////    throws ServletException, IOException {
//////        // Retrieve student from session or request attribute (assuming student is logged in and stored in session)
//////        Student student = (Student) request.getSession().getAttribute("student");
//////        if (student == null) {
//////            // handle case where student is not logged in or not available in session
//////            response.sendRedirect("../view/auth/login.jsp");
//////            return;
//////        }
//////
//////        CourseDBContext db = new CourseDBContext();
//////        int sid = student.getId();
//////        ArrayList<Course> courses = db.getCoursesByStudent(sid);
//////        request.setAttribute("courses", courses);
//////        request.getRequestDispatcher("../view/course/student.jsp").forward(request, response);
//////    }
//////
//////    @Override
//////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//////    throws ServletException, IOException {
//////        // Retrieve student from session or request attribute
//////        Student student = (Student) request.getSession().getAttribute("student");
//////        if (student == null) {
//////            // handle case where student is not logged in or not available in session
//////            response.sendRedirect("../view/auth/login.jsp");
//////            return;
//////        }
//////
//////        int cid = Integer.parseInt(request.getParameter("cid"));
//////        int sid = student.getId();
//////
//////        ExamDBContext db = new ExamDBContext();
//////        ArrayList<Exam> exams = db.getExamsByCourse(cid);
//////        request.setAttribute("exams", exams);
//////
//////        request.getRequestDispatcher("../view/exam/student.jsp").forward(request, response);
//////    }
////
////    @Override
////    public String getServletInfo() {
////        return "Servlet for students to view their courses and exams";
////    }
////
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student) throws ServletException, IOException {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student) throws ServletException, IOException {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////}
//
//package controller.exam;
//
//import controller.auth.BaseRequiredStudentAuthenticationController;
//import dal.CourseDBContext;
//import dal.ExamDBContext;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import model.Course;
//import model.Exam;
//import model.Student;
//import model.User;
//
//public class ViewCourseByStudentController extends BaseRequiredStudentAuthenticationController {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        Student student = (Student) request.getSession().getAttribute("student");
//
//        if (student == null) {
//            // Redirect to login page if student is not found in session
//            response.sendRedirect("../view/auth/login.jsp");
//            return;
//        }
//
//        CourseDBContext db = new CourseDBContext();
//        int sid = student.getId();
//        ArrayList<Course> courses = db.getCoursesByStudent(sid);
//        request.setAttribute("courses", courses);
//      //  request.getRequestDispatcher("../view/course/student.jsp").forward(request, response);
//              request.getRequestDispatcher("../view/exam/viewGrades.jsp").forward(request, response);
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        Student student = (Student) request.getSession().getAttribute("student");
//
//             int cid = Integer.parseInt(request.getParameter("cid"));
//        int sid = student.getId();
//
//        ExamDBContext db = new ExamDBContext();
//        ArrayList<Exam> exams = db.getExamsByCourse(cid);
//        request.setAttribute("exams", exams);
//
//      //  request.getRequestDispatcher("../view/exam/student.jsp").forward(request, response);
//                      request.getRequestDispatcher("../view/exam/viewgrades_1.jsp").forward(request, response);
//
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Servlet for students to view their courses and exams";
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student) throws ServletException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student) throws ServletException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user, Student student) throws ServletException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
package controller.exam;

import controller.auth.BaseRequiredStudentAuthenticationController;
import dal.CourseDBContext;
import dal.ExamDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Exam;
import model.Student;
import model.User;

public class ViewCourseByStudentController extends BaseRequiredStudentAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Student student = (Student) request.getSession().getAttribute("student");

        if (student == null) {
            // Redirect to login page if student is not found in session
            response.sendRedirect("../view/auth/login.jsp");
            return;
        }

        CourseDBContext db = new CourseDBContext();
        int sid = student.getId();
        ArrayList<Course> courses = db.getCoursesByStudent(sid);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../view/exam/viewGrades.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Student student = (Student) request.getSession().getAttribute("student");

        if (student == null) {
            response.sendRedirect("../view/auth/login.jsp");
            return;
        }

        String cidStr = request.getParameter("cid");
        if (cidStr == null || cidStr.isEmpty()) {
            response.sendRedirect("../view/exam/student.jsp");
            return;
        }

        int cid;
        try {
            cid = Integer.parseInt(cidStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("../view/exam/student.jsp");
            return;
        }

        ExamDBContext db = new ExamDBContext();
        ArrayList<Exam> exams = db.getExamsByCourse(cid);
        request.setAttribute("exams", exams);
        request.getRequestDispatcher("../view/exam/viewgrades_1.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for students to view their courses and exams";
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user, Student student) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
