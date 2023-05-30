package by.teachmeskills;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
