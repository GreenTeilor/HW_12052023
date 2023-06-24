package by.teachmeskills.listeners;

import by.teachmeskills.enums.SessionAttributesEnum;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent session) {
        HttpSession httpSession = session.getSession();
        httpSession.setAttribute(SessionAttributesEnum.USER.getValue(), null);
        httpSession.setAttribute(SessionAttributesEnum.CART.getValue(), null);
    }
}
