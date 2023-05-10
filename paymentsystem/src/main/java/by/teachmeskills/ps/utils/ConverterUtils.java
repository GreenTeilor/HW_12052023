package by.teachmeskills.ps.utils;


import by.teachmeskills.ps.model.AccountStatus;

public class ConverterUtils {
    public static AccountStatus toAccountStatus(String status) {
        return status.equals("active") ? AccountStatus.ACTIVE : AccountStatus.DELETED;
    }
}
