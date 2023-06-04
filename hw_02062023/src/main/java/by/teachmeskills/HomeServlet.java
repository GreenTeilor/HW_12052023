package by.teachmeskills;


import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.types.Category;
import by.teachmeskills.types.User;
import by.teachmeskills.utils.DBCrudUtils;
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
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            HttpSession httpSession = request.getSession();
            try {
                request.setAttribute("categories", DBCrudUtils.getCategories());
            } catch (BadConnectionException e) {
                System.out.println(e.getMessage());
            }
            User user = (User)httpSession.getAttribute("user");
            request.setAttribute("name", user.name());
            request.setAttribute("lastName", user.lastName());
            request.setAttribute("balance", user.balance());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
