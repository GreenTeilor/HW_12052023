package by.teachmeskills.enums;

public enum RequestAttributesEnum {
    CATEGORIES("categories"), STATUS("status"), NAME("name"),
    LAST_NAME("lastName"), BALANCE("balance"), CATEGORY_NAME("categoryName"),
    CATEGORY_PRODUCTS("categoryProducts"), PRODUCT_NAME("productName"), PRODUCT("product"),
    CART("cart"), USER("user"), STATISTICS("statistics"), ORDERS("orders");

    private final String VALUE;

    RequestAttributesEnum(String value) {
        this.VALUE = value;
    }

    public String getValue() {
        return VALUE;
    }
}
