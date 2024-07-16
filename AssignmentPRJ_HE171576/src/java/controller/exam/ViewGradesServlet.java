////package controller.exam;
////
////import dal.GradeDBContext;
////import java.io.IOException;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServlet;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import java.util.ArrayList;
////import model.Grade;
////
////public class ViewGradesServlet extends HttpServlet {
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        String sidStr = request.getParameter("sid");
////        String cidStr = request.getParameter("cid");
////
////        if (sidStr == null || sidStr.isEmpty() || cidStr == null || cidStr.isEmpty()) {
////            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID or Course ID is missing");
////            return;
////        }
////
////        int studentID, courseID;
////        try {
////            studentID = Integer.parseInt(sidStr);
////            courseID = Integer.parseInt(cidStr);
////        } catch (NumberFormatException e) {
////            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Student ID or Course ID");
////            return;
////        }
////
////        GradeDBContext gradeDB = new GradeDBContext();
////        ArrayList<Grade> grades = gradeDB.getGradesByCourseAndStudent(courseID, studentID);
////
////        request.setAttribute("grades", grades);
////        request.setAttribute("courseID", courseID);
////        request.getRequestDispatcher("../view/exam/viewgrades_1.jsp").forward(request, response);
////    }
////}
//package controller.exam;
//
//import dal.CourseDBContext;
//import dal.GradeDBContext;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import model.Course;
//import model.Grade;
//import model.Student;
//
//public class ViewGradesServlet extends HttpServlet {
//@Override
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
//        GradeDBContext graDB = new GradeDBContext();
//        ArrayList<Grade> grades = graDB.getGradesByCourseAndStudent(courseID, studentID);
//        request.setAttribute("grades", grades);
//        request.setAttribute("courseID", courseID);
//        request.getRequestDispatcher("../view/exam/viewgrades_1.jsp").forward(request, response);
//    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String sidStr = request.getParameter("sid");
//        String cidStr = request.getParameter("cid");
//
//        System.out.println("Received sid: " + sidStr);
//        System.out.println("Received cid: " + cidStr);
//
//        if (sidStr == null || sidStr.isEmpty() || cidStr == null || cidStr.isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID or Course ID is missing");
//            return;
//        }
//
//        int studentID, courseID;
//        try {
//            studentID = Integer.parseInt(sidStr);
//            courseID = Integer.parseInt(cidStr);
//        } catch (NumberFormatException e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Student ID or Course ID");
//            return;
//        }
//
//        GradeDBContext graDB = new GradeDBContext();
//        ArrayList<Grade> grades = graDB.getGradesByCourseAndStudent(courseID, studentID);
//
//        request.setAttribute("grades", grades);
//        request.setAttribute("courseID", courseID);
//        request.getRequestDispatcher("../view/exam/viewgrades_1.jsp").forward(request, response);
//    }
//}
package controller.exam;

import dal.GradeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Grade;
import model.Student;
import java.io.IOException;
import java.util.ArrayList;

public class ViewGradesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve student from session
        Student student = (Student) request.getSession().getAttribute("student");

        if (student == null) {
            // Redirect to login page if student is not found in session
            response.sendRedirect(request.getContextPath() + "/view/auth/login.jsp");
            return;
        }

        // Retrieve courseID from request parameter
        String cidStr = request.getParameter("cid");

        if (cidStr == null || cidStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Course ID is missing");
            return;
        }

        int courseID;
        try {
            courseID = Integer.parseInt(cidStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Course ID");
            return;
        }

        // Retrieve grades for the student and course
        GradeDBContext graDB = new GradeDBContext();
        ArrayList<Grade> grades = graDB.getGradesByCourseAndStudent(courseID, student.getId());

        // Set attributes and forward to viewgrades.jsp
        request.setAttribute("grades", grades);
        request.setAttribute("courseID", courseID);
        request.getRequestDispatcher("/view/exam/viewgrades_1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests if needed (e.g., for form submissions)
        doGet(request, response); // Delegate to doGet for handling
    }
}
