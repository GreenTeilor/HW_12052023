package by.teachmeskills.client;

import by.teachmeskills.ps.model.BankAccount;
import by.teachmeskills.ps.model.Merchant;
import by.teachmeskills.ps.service.MerchantService;
import by.teachmeskills.ps.utils.DbUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        try {
            MerchantService service = new MerchantService(DbUtils::getConnection);
            Scanner scanner = new Scanner(System.in);
            boolean isWorking = true;
            while (isWorking) {
                System.out.print("""
                        Options:
                        1) Get info about merchant bank accounts
                        2) Add new bank account to merchant
                        3) Edit merchant bank account number
                        4) Delete merchant bank account
                        5) Get merchant info
                        6) Get all merchants
                        7) Create merchant
                        8) Delete merchant
                        9) Exit
                        Which option you choose: """);
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        service.getMerchantBankAccounts(merchant).forEach(System.out::println);
                    }

                    case 2 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        System.out.print("Input account number: ");
                        BankAccount account = new BankAccount(merchant.getId(), scanner.next());
                        service.addBankAccount(merchant, account);
                    }

                    case 3 -> {
                        System.out.print("Input merchant id and account number: ");
                        BankAccount account = service.getAccount(scanner.next(), scanner.next());
                        System.out.print("Input new bank account number: ");
                        service.updateBankAccount(account, scanner.next());
                    }

                    case 4 -> {
                        System.out.print("Input merchant id and account number: ");
                        BankAccount account = service.getAccount(scanner.next(), scanner.next());
                        service.deleteBankAccount(account);
                    }

                    case 5 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            System.out.println(merchant);
                        } else {
                            System.out.println("There is no such merchant!");
                        }
                    }

                    case 6 -> service.getMerchants().forEach(System.out::println);

                    case 7 -> {
                        System.out.print("Input merchant name: ");
                        service.createMerchant(scanner.next());
                    }

                    case 8 -> {
                        System.out.print("Input merchant id: ");
                        service.deleteMerchant(service.getMerchantById(scanner.next()));
                    }

                    default -> {
                        System.out.println("Exit");
                        isWorking = false;
                    }
                }
            }
            service.finishWork();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
