///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controller.auth;
//
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import model.Student;
//import model.User;
//
///**
// *
// * @author
// */
//public abstract class BaseRequiredStudentAuthenticationController extends HttpServlet {
//   
//   private boolean isAuthenticated(HttpServletRequest request) {
//    User user = (User) request.getSession().getAttribute("user");
//    if (user == null)
//        return false;
//    else {
//        Student student = user.getStudent();
//        return student != null;
//    }
//}
//
//    
////     private boolean isAuthenticated(HttpServletRequest request)
////    {
////        User user = (User)request.getSession().getAttribute("user");
////        if(user ==null)
////            return false;
////        else
////        {
////            Student student = user.getStudent();
////            return student != null;
////        }
////    }
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /** 
//     * Handles the HTTP <code>GET</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        User user = (User)request.getSession().getAttribute("user");
//
//        if (isAuthenticated(request)) {
//            doGet(request, response, user, user.getStudent());
//        } else {
//            response.getWriter().println("Access denied do get!");
//        }
//    } 
//    
//    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student)
//    throws ServletException, IOException;
//
//    /** 
//     * Handles the HTTP <code>POST</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        User user = (User)request.getSession().getAttribute("user");
//
//        if (isAuthenticated(request)) {
//            doPost(request, response, user, user.getStudent());
//        } else {
//            response.getWriter().println("Access denied!");
//        }
//    }
//
//    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, User user, Student student)
//    throws ServletException, IOException;
//
//    /** 
//     * Returns a short description of the servlet.
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//}
package controller.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import model.User;

public abstract class BaseRequiredStudentAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return false;
        } else {
            Student student = user.getStudent();
            return student != null;
        }
    }

    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        if (isAuthenticated(request)) {
//            User user = (User) request.getSession().getAttribute("user");
//            processRequest(request, response, user, user.getStudent());
//        } else {
//            response.getWriter().println("Access denied!");
//        }
//    }
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(isAuthenticated(request))
        {
            doGet(request, response, user, user.getStudent());
        }
        else
        {
            response.getWriter().println("access denied get ne!");
        }
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            User user = (User) request.getSession().getAttribute("user");
            processRequest(request, response, user, user.getStudent());
        } else {
            response.getWriter().println("Access denied!");
        }
    }

    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response, User user, Student student)
            throws ServletException, IOException;

    private void doGet(HttpServletRequest request, HttpServletResponse response, User user, Student student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
