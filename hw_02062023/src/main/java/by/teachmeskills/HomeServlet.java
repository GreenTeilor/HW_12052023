package by.teachmeskills;


import by.teachmeskills.types.Category;
import by.teachmeskills.types.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            HttpSession httpSession = request.getSession();
            request.setAttribute("categories", getServletContext().getAttribute("categories"));
            User user = (User)httpSession.getAttribute("loggedIn");
            request.setAttribute("name", user.name());
            request.setAttribute("lastName", user.lastName());
            request.setAttribute("balance", user.balance());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
