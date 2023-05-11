package by.teachmeskills.ps.model;

import java.time.LocalDate;

public class BankAccount implements Comparable<BankAccount> {
    private String merchantId;
    private AccountStatus status;
    private String accountNumber;
    LocalDate createdAt;

    //Constructor to add new account
    public BankAccount(String merchantId, String accountNumber) {
        this.merchantId = merchantId;
        this.status = AccountStatus.ACTIVE;
        this.accountNumber = accountNumber;
        this.createdAt = LocalDate.now();
    }

    //Constructor to read bank account info from file
    public BankAccount(String merchantId, AccountStatus status, String accountNumber, LocalDate createdAt) {
        this.merchantId = merchantId;
        this.status = status;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public LocalDate getCreationDate() {
        return createdAt;
    }

    @Override
    public int compareTo(BankAccount bankAccount) {
        if (this.status != bankAccount.status) {
            return this.status == AccountStatus.ACTIVE ? -1 : 1;
        }
        return this.createdAt.compareTo(bankAccount.createdAt);
    }

    @Override
    public String toString() {
        return merchantId + " " + status + " " + accountNumber + " " + createdAt;
    }
}
