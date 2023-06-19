package by.teachmeskills.enums;

public enum SessionAttributesEnum {
    USER("user");
    private final String VALUE;
    SessionAttributesEnum(String value) {
        this.VALUE = value;
    }

    public String getValue() {
        return VALUE;
    }
}
