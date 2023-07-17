package by.teachmeskills.repositories;

import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;

public interface UserRepository extends BaseRepository<User>{
    User getUserByEmail(String email) throws BadConnectionException;
    User getUserById(int id) throws BadConnectionException;
    User getUser(String email, String password) throws BadConnectionException;
    void updateAddressAndPhoneNumber(String address, String phoneNumber, String email) throws BadConnectionException;
}
