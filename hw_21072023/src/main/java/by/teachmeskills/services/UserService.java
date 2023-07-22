package by.teachmeskills.services;

import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface UserService extends BaseService<User>{
    User getUserByEmail(String email) throws BadConnectionException;
    User getUserById(int id) throws BadConnectionException;
    ModelAndView getUser(String email, String password, Model model);
    void updateAddressAndPhoneNumber(String address, String phoneNumber, String email) throws BadConnectionException;
}
