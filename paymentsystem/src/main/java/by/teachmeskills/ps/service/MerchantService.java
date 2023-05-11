package by.teachmeskills.ps.service;

import by.teachmeskills.ps.model.BankAccount;
import by.teachmeskills.ps.model.Merchant;
import by.teachmeskills.ps.utils.DbCRUDUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MerchantService {

    public MerchantService(Supplier<Connection> connector) throws SQLException {
        DbCRUDUtils.setConnection(connector);
    }

    public BankAccount addBankAccount(Merchant merchant, BankAccount bankAccount) {
        if (merchant != null && bankAccount.getAccountNumber().length() == 8 && bankAccount.getAccountNumber().matches("\\d{8}")) {
            try {
                DbCRUDUtils.saveBankAccountDB(bankAccount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There is no such merchant or account number is incorrect!");
        }
        return bankAccount;
    }

    public List<BankAccount> getMerchantBankAccounts(Merchant merchant) {
        if (merchant != null) {
            return DbCRUDUtils.readMerchantBankAccounts(merchant);
        } else {
            System.out.println("There's no such merchant!");
            return new ArrayList<>();
        }
    }

    public BankAccount updateBankAccount(BankAccount bankAccount, String newBankAccountNumber) {
        if (bankAccount != null) {
            DbCRUDUtils.updateBankAccountDB(bankAccount, newBankAccountNumber);
        } else {
            System.out.println("There is no such account!");
        }
        return bankAccount;
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        if (bankAccount != null) {
            DbCRUDUtils.deleteBankAccountDB(bankAccount);
        } else {
            System.out.println("There is no such account!");
        }
    }

    public void createMerchant(String name) {
        DbCRUDUtils.saveMerchantDB(name);
    }

    public List<Merchant> getMerchants() {
        return DbCRUDUtils.readMerchantsDB();
    }

    public Merchant getMerchantById(String id) {
        return DbCRUDUtils.getMerchantByIdDB(id);
    }

    public void deleteMerchant(Merchant merchant) {
        if (merchant != null) {
            DbCRUDUtils.deleteMerchantDB(merchant);
            DbCRUDUtils.deleteMerchantBankAccountsDB(merchant);
        } else {
            System.out.println("There is no such merchant!");
        }
    }

    public BankAccount getAccount(String merchant_id, String account_number) {
        return DbCRUDUtils.getBankAccountDB(merchant_id, account_number);
    }

    public void finishWork() {
        try {
            DbCRUDUtils.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
