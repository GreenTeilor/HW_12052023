package by.teachmeskills;

import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.types.User;
import by.teachmeskills.utils.DBCrudUtils;
import by.teachmeskills.utils.ValidatorUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        ValidatorUtils.Status status = ValidatorUtils.validateForm(name, lastName, email, birthDate, password);
        if (status == ValidatorUtils.Status.VALID) {
            try {
                DBCrudUtils.addUser(new User(name, lastName, email, birthDate, BigDecimal.valueOf(0.0), password));
                request.setAttribute("status", status.toString());
                request.setAttribute("color", "green");
            } catch (BadConnectionException e) {
                System.out.println(e.getMessage());
            } catch (UserAlreadyExistsException e) {
                request.setAttribute("status", e.getMessage());
                request.setAttribute("color", "red");
            }
        } else {
            request.setAttribute("status", status.toString());
            request.setAttribute("color", "red");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
        requestDispatcher.forward(request, response);
    }
}
