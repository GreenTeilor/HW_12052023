package by.teachmeskills.services;

import by.teachmeskills.entities.BaseEntity;
import by.teachmeskills.exceptions.BadConnectionException;
import org.springframework.web.servlet.ModelAndView;

public interface BaseService<T extends BaseEntity> {
    ModelAndView create(T entity);

    ModelAndView read();

    T update(T entity) throws BadConnectionException;

    void delete(int id) throws BadConnectionException;
}
