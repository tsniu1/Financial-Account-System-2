package com.bunabank.model;

public abstract class Account {
   
    private String accountNumber;
    private double balance;
    private String accountHolderName;

   
    public Account() {
        this("UNKNOWN", 0.0, "UNKNOWN");
        System.out.println("[Implicit super() demo] No-arg Account constructor called.");
    }

  
    public Account(String accountNumber, double balance, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolderName = accountHolderName;
    }

    
    public Account(Account other) {
        this(other.accountNumber, other.balance, other.accountHolderName);
    }

    
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    protected void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

  
    public final String getBankName() {
        return "Buna Bank";
    }
    
    public abstract double calculateInterest();

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited " + amount + " to account " + accountNumber);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from account " + accountNumber);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void displayInfo() {
        System.out.println("Account No: " + accountNumber + ", Holder: " + accountHolderName +
                           ", Balance: " + balance);
    }
    public static void displayBankInfo() {
        System.out.println("Buna Bank – Established 2024 – Trust & Growth");
    }
}