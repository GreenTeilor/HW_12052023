package by.teachmeskills;

import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.types.User;
import by.teachmeskills.utils.DBCrudUtils;
import by.teachmeskills.utils.HashUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher;
        try {
            User user = DBCrudUtils.getUser(request.getParameter("email"), HashUtils.getHash(request.getParameter("password")));
            if (user != null) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("loggedIn", user);
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
                request.setAttribute("result", "Неверный логин или пароль");
                requestDispatcher.forward(request, response);
            }
        } catch (BadConnectionException e) {
            System.out.println("Can't login due to bad connection");
        }
    }
}
