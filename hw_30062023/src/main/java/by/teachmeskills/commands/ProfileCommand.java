package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.enums.SessionAttributesEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.types.Order;
import by.teachmeskills.types.Product;
import by.teachmeskills.types.Statistics;
import by.teachmeskills.types.User;
import by.teachmeskills.utils.DBCrudUtils;
import by.teachmeskills.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfileCommand implements BaseCommand{
    private static final Logger logger = LoggerFactory.getLogger(ProfileCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String address = request.getParameter(RequestParametersEnum.ADDRESS.getValue());
        String phoneNumber = request.getParameter(RequestParametersEnum.PHONE_NUMBER.getValue());
        if (address != null && phoneNumber != null) {
            try {
                if (ValidatorUtils.isValidAddress(address) && ValidatorUtils.isValidPhoneNumber(phoneNumber)) {
                    String email = ((User)session.getAttribute(SessionAttributesEnum.USER.getValue())).email();
                    DBCrudUtils.updateAddressAndPhoneNumber(address, phoneNumber, email);
                    session.setAttribute(SessionAttributesEnum.USER.getValue(), DBCrudUtils.getUserByEmail(email));
                }
            } catch (BadConnectionException e) {
                logger.error(e.getMessage());
            }
        }
        request.setAttribute(RequestAttributesEnum.USER.getValue(), session.getAttribute(SessionAttributesEnum.USER.getValue()));
        request.setAttribute(RequestAttributesEnum.STATISTICS.getValue(), new Statistics(10, 2, 5, "Фантастика"));
        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();
        try {
            list1 = new ArrayList<>(List.of(DBCrudUtils.getProduct(1), DBCrudUtils.getProduct(2), DBCrudUtils.getProduct(3)));
            list2 = new ArrayList<>(List.of(DBCrudUtils.getProduct(2), DBCrudUtils.getProduct(1)));
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        List<Order> orders = new ArrayList<>(List.of(new Order(1, LocalDate.now(), list1), new Order(2, LocalDate.now(), list2)));
        request.setAttribute(RequestAttributesEnum.ORDERS.getValue(), orders);
        return PagesPathsEnum.PROFILE_PAGE.getPath();
    }
}
