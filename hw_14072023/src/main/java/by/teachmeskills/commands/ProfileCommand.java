package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.enums.SessionAttributesEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.entities.Order;
import by.teachmeskills.entities.Product;
import by.teachmeskills.entities.Statistics;
import by.teachmeskills.entities.User;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.UserService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import by.teachmeskills.services.implementation.UserServiceImplementation;
import by.teachmeskills.utils.ValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfileCommand implements BaseCommand{
    private static final Logger logger = LoggerFactory.getLogger(ProfileCommand.class);
    private static final ProductService productService = new ProductServiceImplementation();
    private static final UserService userService = new UserServiceImplementation();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String address = request.getParameter(RequestParametersEnum.ADDRESS.getValue());
        String phoneNumber = request.getParameter(RequestParametersEnum.PHONE_NUMBER.getValue());
        if (address != null && phoneNumber != null) {
            try {
                if (ValidatorUtils.isValidAddress(address) && ValidatorUtils.isValidPhoneNumber(phoneNumber)) {
                    String email = ((User)session.getAttribute(SessionAttributesEnum.USER.getValue())).getEmail();
                    userService.updateAddressAndPhoneNumber(address, phoneNumber, email);
                    session.setAttribute(SessionAttributesEnum.USER.getValue(), userService.getUserByEmail(email));
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
            list1 = new ArrayList<>(List.of(productService.getProductById(1), productService.getProductById(2), productService.getProductById(3)));
            list2 = new ArrayList<>(List.of(productService.getProductById(2), productService.getProductById(1)));
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        List<Order> orders = new ArrayList<>(List.of(Order.builder().id(1).date(LocalDate.now()).products(list1).userId(2).price(BigDecimal.valueOf(40.0)).build(),
                Order.builder().id(1).date(LocalDate.now()).products(list2).userId(2).price(BigDecimal.valueOf(50.0)).build()));
        request.setAttribute(RequestAttributesEnum.ORDERS.getValue(), orders);
        return PagesPathsEnum.PROFILE_PAGE.getPath();
    }
}
