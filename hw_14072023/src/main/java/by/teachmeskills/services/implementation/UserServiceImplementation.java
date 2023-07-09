package by.teachmeskills.services.implementation;

import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.repositories.UserRepository;
import by.teachmeskills.repositories.implementation.UserRepositoryImplementation;
import by.teachmeskills.services.UserService;

import java.util.List;

public class UserServiceImplementation implements UserService {
    UserRepository userRepository = new UserRepositoryImplementation();
    @Override
    public User getUserByEmail(String email) throws BadConnectionException {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserById(int id) throws BadConnectionException {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUser(String email, String password) throws BadConnectionException {
        return userRepository.getUser(email, password);
    }

    @Override
    public void updateAddressAndPhoneNumber(String address, String phoneNumber, String email) throws BadConnectionException {
        userRepository.updateAddressAndPhoneNumber(address, phoneNumber, email);
    }

    @Override
    public User create(User user) throws BadConnectionException, UserAlreadyExistsException {
        return userRepository.create(user);
    }

    @Override
    public List<User> read() throws BadConnectionException {
        return userRepository.read();
    }

    @Override
    public User update(User user) throws BadConnectionException {
        return userRepository.update(user);
    }

    @Override
    public void delete(int id) throws BadConnectionException {
        userRepository.delete(id);
    }
}
