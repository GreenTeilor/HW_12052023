package by.teachmeskills;

import by.teachmeskills.commands.BaseCommand;
import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.utils.CommandFactoryUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/shop")
public class AppServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BaseCommand requestCommand = CommandFactoryUtils.defineCommand(request);
        String path;
        try {
            path = requestCommand.execute(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (CommandException e) {
            request.getRequestDispatcher(PagesPathsEnum.LOGIN_PAGE.getPath()).forward(request, response);
        }
    }
}
