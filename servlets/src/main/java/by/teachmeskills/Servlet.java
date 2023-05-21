package by.teachmeskills;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String operand1 = request.getParameter("operand1");
        String operand2 = request.getParameter("operand2");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        String operation = request.getParameterValues("operation")[0];

        if (operation != null) {
            switch (operation) {
                case "sumOperation" ->
                        request.setAttribute("result", CalculatorUtils.sum(Integer.parseInt(operand1), Integer.parseInt(operand2)));
                case "subOperation" ->
                        request.setAttribute("result", CalculatorUtils.sub(Integer.parseInt(operand1), Integer.parseInt(operand2)));
                case "mulOperation" ->
                        request.setAttribute("result", CalculatorUtils.mul(Integer.parseInt(operand1), Integer.parseInt(operand2)));
                case "divOperation" ->
                        request.setAttribute("result", CalculatorUtils.div(Integer.parseInt(operand1), Integer.parseInt(operand2)));
            }
        }
        requestDispatcher.forward(request, response);
    }
}