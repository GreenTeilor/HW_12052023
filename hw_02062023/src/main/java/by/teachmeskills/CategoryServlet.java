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

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/category.jsp");
        try {
            request.setAttribute("categoryName", request.getParameter("name"));
            request.setAttribute("productsInCategory", DBCrudUtils.getProductsInCategory(request.getParameter("name")));
        } catch (BadConnectionException e) {
            System.out.println(e.getMessage());
        }
        requestDispatcher.forward(request, response);
    }
}
