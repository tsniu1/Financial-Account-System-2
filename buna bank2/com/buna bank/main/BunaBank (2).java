package com.bunabank.main;

import com.bunabank.model.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BunaBank {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Buna Bank (Enhanced Version) ===\n");
        
       
        accounts.add(new SavingsAccount("SA1001", 2000, "Alice Wonder", 3.5));
        accounts.add(new CheckingAccount("CA2001", 500, "Bob Builder", 200));
        accounts.add(new FixedDepositAccount("FD3001", 10000, "Charlie Brown", 6.0, 12));
        
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = readIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        displayAllAccounts();
                        break;
                    case 5:
                        showPolymorphismDemo();
                        break;
                    case 6:
                        showAbstractionDemo();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Thank you for banking with Buna Bank!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-7.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Buna Bank Menu ---");
        System.out.println("1. Create a new account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Display all accounts");
        System.out.println("5. Polymorphism demo (call same method on all accounts)");
        System.out.println("6. Abstraction demo (calculate interest for all accounts)");
        System.out.println("7. Exit");
    }

    private static int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void createAccount() {
        System.out.println("\n--- Create New Account ---");
        System.out.println("Select account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.println("3. Fixed Deposit Account");
        int type = readIntInput("Choice: ");
        
        System.out.print("Enter account number: ");
        String accNo = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String holder = scanner.nextLine();
        double initialBalance = readDoubleInput("Enter initial balance: ");
        
        try {
            if (initialBalance < 0) throw new IllegalArgumentException("Balance cannot be negative.");
            
            switch (type) {
                case 1:
                    double rate = readDoubleInput("Enter interest rate (%): ");
                    accounts.add(new SavingsAccount(accNo, initialBalance, holder, rate));
                    break;
                case 2:
                    double overdraft = readDoubleInput("Enter overdraft limit: ");
                    accounts.add(new CheckingAccount(accNo, initialBalance, holder, overdraft));
                    break;
                case 3:
                    double fdRate = readDoubleInput("Enter interest rate (%): ");
                    int tenure = readIntInput("Enter tenure (months): ");
                    accounts.add(new FixedDepositAccount(accNo, initialBalance, holder, fdRate, tenure));
                    break;
                default:
                    System.out.println("Invalid account type.");
            }
            TransactionLogger.logTransaction("New account created: " + accNo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void depositMoney() {
        displayAllAccounts();
        int index = readIntInput("Enter account number index (0 to " + (accounts.size()-1) + "): ");
        if (index >= 0 && index < accounts.size()) {
            double amount = readDoubleInput("Enter deposit amount: ");
            accounts.get(index).deposit(amount);
            TransactionLogger.logTransaction("Deposited " + amount + " to " + accounts.get(index).getAccountNumber());
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void withdrawMoney() {
        displayAllAccounts();
        int index = readIntInput("Enter account number index (0 to " + (accounts.size()-1) + "): ");
        if (index >= 0 && index < accounts.size()) {
            double amount = readDoubleInput("Enter withdrawal amount: ");
            accounts.get(index).withdraw(amount);
            TransactionLogger.logTransaction("Withdrew " + amount + " from " + accounts.get(index).getAccountNumber());
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("\n=== All Accounts ===");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.print(i + ". ");
            accounts.get(i).displayInfo();
        }
    }

    private static void showPolymorphismDemo() {
        System.out.println("\n--- Polymorphism Demo ---");
        for (Account acc : accounts) {
            System.out.print(acc.getAccountNumber() + " -> ");
            acc.displayInfo();  
        }
    }

    private static void showAbstractionDemo() {
        System.out.println("\n--- Abstraction Demo (Interest Calculation) ---");
        for (Account acc : accounts) {
            double interest = acc.calculateInterest();
            System.out.println("Account " + acc.getAccountNumber() + " interest earned: " + interest);
        }
    }
}