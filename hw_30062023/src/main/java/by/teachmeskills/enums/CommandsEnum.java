package by.teachmeskills.enums;

public enum CommandsEnum {
    HOME_PAGE_COMMAND("home_page"),
    LOGIN_PAGE_COMMAND("login_page"),
    REGISTER_PAGE_COMMAND("register_page"),
    ABOUT_PAGE_COMMAND("about_page"),
    CATEGORY_PAGE_COMMAND("category_page"),
    PRODUCT_PAGE_COMMAND("product_page"),
    CART_PAGE_COMMAND("cart_page"),
    PROFILE_PAGE_COMMAND("profile_page");


    private final String COMMAND;

    CommandsEnum(String command) {
        this.COMMAND = command;
    }

    public String getCommand() {
        return COMMAND;
    }
}
