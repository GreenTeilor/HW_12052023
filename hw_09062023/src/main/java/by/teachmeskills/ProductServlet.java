package by.teachmeskills;

import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.utils.DBCrudUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/product.jsp");
        try {
            request.setAttribute("product", DBCrudUtils.getProduct(Integer.parseInt(request.getParameter("id"))));
        } catch (BadConnectionException e) {
            System.out.println(e.getMessage());
        }
        requestDispatcher.forward(request, response);
    }
}
