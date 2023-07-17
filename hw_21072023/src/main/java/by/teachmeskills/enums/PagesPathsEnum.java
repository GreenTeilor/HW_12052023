package by.teachmeskills.enums;

public enum PagesPathsEnum {
    HOME_PAGE("/WEB-INF/views/home.jsp"),
    LOGIN_PAGE("/WEB-INF/views/login.jsp"),
    REGISTER_PAGE("/WEB-INF/views/register.jsp"),
    CATEGORY_PAGE("/WEB-INF/views/category.jsp"),
    ABOUT_PAGE("/WEB-INF/views/aboutAuthor.jsp"),
    PRODUCT_PAGE("/WEB-INF/views/product.jsp"),
    CART_PAGE("/WEB-INF/views/cart.jsp"),
    PROFILE_PAGE("/WEB-INF/views/profile.jsp"),
    SEARCH_PAGE("/WEB-INF/views/search.jsp");

    private final String PATH;

    PagesPathsEnum(String path) {
        this.PATH = path;
    }

    public String getPath() {
        return PATH;
    }
}
