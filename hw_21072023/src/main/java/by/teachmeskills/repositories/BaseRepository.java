package by.teachmeskills.repositories;

import by.teachmeskills.entities.BaseEntity;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    ConnectionPool pool = ConnectionPool.getInstance();

    T create(T entity) throws BadConnectionException, UserAlreadyExistsException;

    List<T> read() throws BadConnectionException;

    T update(T entity) throws BadConnectionException;

    void delete(int id) throws BadConnectionException;
}
