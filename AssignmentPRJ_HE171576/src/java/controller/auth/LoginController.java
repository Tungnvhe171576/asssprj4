package controller.auth;

import dal.LecturerDBContext;
import dal.StudentDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Lecturer;
import model.Student;
import model.User;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDBContext userDb = new UserDBContext();
        User user = userDb.getUserByUsernamePassword(username, password);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            LecturerDBContext lecturerDb = new LecturerDBContext();
            Lecturer lecturer = lecturerDb.getLecturerByUsername(username);

            if (lecturer != null) {
                user.setLecturer(lecturer); // Set the lecturer in the user object
                session.setAttribute("lecturer", lecturer);
                response.sendRedirect("exam/lecturer");
            } else {
                StudentDBContext studentDb = new StudentDBContext();
                Student student = studentDb.getStudentByUsername(username);
                if (student != null) {
                    session.setAttribute("student", student);
                    response.sendRedirect("exam/student");
                } else {
                    response.getWriter().println("User role not recognized.");
                }
            }
        } else {
            response.getWriter().println("Login failed!");
        }
    }
}
