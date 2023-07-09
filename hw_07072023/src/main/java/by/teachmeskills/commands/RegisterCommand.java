package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.entities.User;
import by.teachmeskills.services.UserService;
import by.teachmeskills.services.implementation.UserServiceImplementation;
import by.teachmeskills.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static by.teachmeskills.utils.HttpRequestParamValidatorUtils.validateParametersNotNull;

public class RegisterCommand implements BaseCommand {
    private static final Logger logger = LoggerFactory.getLogger(RegisterCommand.class);
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
        UserService service = new UserServiceImplementation();
        if (status == ValidatorUtils.Status.VALID) {
            try {
                service.create(User.builder().name(name).lastName(lastName).email(email).birthDate(birthDate).
                        registrationDate(LocalDate.now()).balance(BigDecimal.valueOf(0.0)).password(password).address(null).phoneNumber(null).build());
                request.setAttribute("status", status.toString());
                request.setAttribute("color", "green");
            } catch (BadConnectionException e) {
                logger.error(e.getMessage());
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
