package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AboutCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PagesPathsEnum.ABOUT_PAGE.getPath();
    }
}
