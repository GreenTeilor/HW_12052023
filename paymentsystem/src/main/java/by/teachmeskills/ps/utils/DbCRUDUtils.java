package by.teachmeskills.ps.utils;


import by.teachmeskills.ps.model.BankAccount;
import by.teachmeskills.ps.model.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class DbCRUDUtils {
    private final static String ADD_QUERY = "INSERT INTO merchants (id, name, created_at) Values (?, ?, ?)";
    private final static String SEARCH_ACCOUNT_QUERY = "SELECT * FROM bank_accounts WHERE merchant_id = ? AND account_number = ?";
    private final static String UPDATE_STATUS_QUERY = "UPDATE bank_accounts SET status = ? WHERE merchant_id = ? AND account_number = ?";
    private final static String CREATE_QUERY = "INSERT INTO bank_accounts (id, merchant_id, status, account_number, created_at) Values (?, ?, ?, ?, ?)";
    private final static String GET_MERCHANTS_QUERY = "SELECT * FROM merchants";
    private final static String UPDATE_ACCOUNT_NUMBER_QUERY = "UPDATE bank_accounts SET account_number = ? WHERE merchant_id = ? AND account_number = ?";
    private final static String DELETE_ACCOUNTS_QUERY = "DELETE FROM bank_accounts WHERE merchant_id = ?";
    private final static String DELETE_MERCHANT_QUERY = "DELETE FROM merchants WHERE id = ?";
    private final static String GET_MERCHANT_ACCOUNTS_QUERY = "SELECT * FROM bank_accounts WHERE merchant_id = ? ORDER BY status ASC, created_at ASC";
    private final static String GET_MERCHANT_BY_ID_QUERY = "SELECT * FROM merchants WHERE id = ?";
    private static Connection connection;

    private DbCRUDUtils() {

    }

    public static void setConnection(Supplier<Connection> connector) throws SQLException{
        connection = connector.get();
        if (connection == null) {
            throw new SQLException("Connection is not established");
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void saveMerchantDB(String name) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_QUERY)) {
            statement.setString(1, String.valueOf(UUID.randomUUID()));
            statement.setString(2, name);
            statement.setTimestamp(3, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveBankAccountDB(BankAccount bankAccount) {
        Function<String, Boolean> isPresent = query -> {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, bankAccount.getMerchantId());
                statement.setString(2, EncryptionUtils.encrypt(bankAccount.getAccountNumber()));
                ResultSet set = statement.executeQuery();
                int size = 0;
                while (set.next()) {
                    ++size;
                }
                set.close();
                return size != 0;
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        };
        if (isPresent.apply(SEARCH_ACCOUNT_QUERY)) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_QUERY)) {
                statement.setString(1, "active");
                statement.setString(2, bankAccount.getMerchantId());
                statement.setString(3, EncryptionUtils.encrypt(bankAccount.getAccountNumber()));
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
                statement.setString(1, String.valueOf(UUID.randomUUID()));
                statement.setString(2, bankAccount.getMerchantId());
                statement.setString(3, bankAccount.getStatus().toString());
                statement.setString(4, EncryptionUtils.encrypt(bankAccount.getAccountNumber()));
                statement.setTimestamp(5, Timestamp.valueOf(bankAccount.getCreationDate().atStartOfDay()));
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static List<Merchant> readMerchantsDB() {
        List<Merchant> merchants = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(GET_MERCHANTS_QUERY);
            while (set.next()) {
                merchants.add(new Merchant(set.getString(1), set.getString(2), set.getTimestamp(3).toLocalDateTime().toLocalDate()));
            }
            set.close(); //HERE TO DISCUSS
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchants;
    }

    public static void deleteBankAccountDB(BankAccount account) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_QUERY)) {
            statement.setString(1, "deleted");
            statement.setString(2, account.getMerchantId());
            statement.setString(3, EncryptionUtils.encrypt(account.getAccountNumber()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMerchantBankAccountsDB(Merchant merchant) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNTS_QUERY)) {
            statement.setString(1, merchant.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMerchantDB(Merchant merchant) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MERCHANT_QUERY)) {
            statement.setString(1, merchant.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBankAccountDB(BankAccount bankAccount, String newBankAccountNumber) {
        Function<String, Boolean> isPresent = newNumber -> {
            try (PreparedStatement statement = connection.prepareStatement(SEARCH_ACCOUNT_QUERY)) {
                statement.setString(1, bankAccount.getMerchantId());
                statement.setString(2, EncryptionUtils.encrypt(newNumber));
                ResultSet set = statement.executeQuery();
                int size = 0;
                while (set.next()) {
                    ++size;
                }
                set.close();
                return size != 0;
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        };
        if (!isPresent.apply(newBankAccountNumber)) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_NUMBER_QUERY)) {
                statement.setString(1, EncryptionUtils.encrypt(newBankAccountNumber));
                statement.setString(2, bankAccount.getMerchantId());
                statement.setString(3, EncryptionUtils.encrypt(bankAccount.getAccountNumber()));
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<BankAccount> readMerchantBankAccounts(Merchant merchant) {
        List<BankAccount> accounts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_MERCHANT_ACCOUNTS_QUERY)) {
            statement.setString(1, merchant.getId());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                accounts.add(new BankAccount(set.getString(2), ConverterUtils.toAccountStatus(set.getString(3)),
                        EncryptionUtils.decrypt(set.getString(4)), set.getTimestamp(5).toLocalDateTime().toLocalDate()));
            }
            set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    public static Merchant getMerchantByIdDB(String id) {
        Merchant merchant = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_MERCHANT_BY_ID_QUERY)) {
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                merchant = new Merchant(set.getString(1), set.getString(2), set.getTimestamp(3).toLocalDateTime().toLocalDate());
            }
            set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchant;
    }

    public static BankAccount getBankAccountDB(String merchant_id, String account_number) {
        BankAccount account = null;
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_ACCOUNT_QUERY)) {
            statement.setString(1, merchant_id);
            statement.setString(2, EncryptionUtils.encrypt(account_number));
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                account = new BankAccount(set.getString(2), ConverterUtils.toAccountStatus(set.getString(3)),
                        EncryptionUtils.decrypt(set.getString(4)), set.getTimestamp(5).toLocalDateTime().toLocalDate());
            }
            set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }
}
