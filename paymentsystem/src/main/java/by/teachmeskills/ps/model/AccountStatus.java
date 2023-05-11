package by.teachmeskills.ps.model;

public enum AccountStatus {
    ACTIVE("active"), DELETED("deleted");
    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
