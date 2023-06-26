package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.types.User;
import by.teachmeskills.utils.DBCrudUtils;
import by.teachmeskills.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static by.teachmeskills.utils.HttpRequestParamValidatorUtils.validateParametersNotNull;

public class RegisterCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String name = request.getParameter(RequestParametersEnum.NAME.getValue());
        String lastName = request.getParameter(RequestParametersEnum.LAST_NAME.getValue());
        String email = request.getParameter(RequestParametersEnum.EMAIL.getValue());
        String password = request.getParameter(RequestParametersEnum.PASSWORD.getValue());
        String date = request.getParameter(RequestParametersEnum.BIRTH_DATE.getValue());

        try {
            validateParametersNotNull(name, lastName, email, password, date);
        } catch (CommandException e) {
            return PagesPathsEnum.REGISTER_PAGE.getPath();
        }
        LocalDate birthDate = LocalDate.parse(date);

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
        return PagesPathsEnum.REGISTER_PAGE.getPath();
    }
}
