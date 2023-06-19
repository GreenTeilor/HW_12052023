package by.teachmeskills.utils;

import by.teachmeskills.commands.*;
import by.teachmeskills.enums.CommandsEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactoryUtils {
    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(CommandsEnum.LOGIN_PAGE_COMMAND.getCommand(), new LoginCommand());
        COMMAND_LIST.put(CommandsEnum.REGISTER_PAGE_COMMAND.getCommand(), new RegisterCommand());
        COMMAND_LIST.put(CommandsEnum.HOME_PAGE_COMMAND.getCommand(), new HomeCommand());
        COMMAND_LIST.put(CommandsEnum.ABOUT_PAGE_COMMAND.getCommand(), new AboutCommand());
        COMMAND_LIST.put(CommandsEnum.CATEGORY_PAGE_COMMAND.getCommand(), new CategoryCommand());
        COMMAND_LIST.put(CommandsEnum.PRODUCT_PAGE_COMMAND.getCommand(), new ProductCommand());
    }

    public static BaseCommand defineCommand(HttpServletRequest request) {
        String commandKey = request.getParameter(RequestParametersEnum.COMMAND.getValue());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = CommandsEnum.LOGIN_PAGE_COMMAND.getCommand();
        }
        return COMMAND_LIST.get(commandKey);
    }
}
