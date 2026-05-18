package com.bunabank.model;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, String accountHolderName, double overdraftLimit) {
        super(accountNumber, balance, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (getBalance() + overdraftLimit) >= amount) {
            setBalance(getBalance() - amount); 
            System.out.println("Checking withdrawal: " + amount + " (Overdraft limit left: " + (overdraftLimit + getBalance()) + ")");
        } else {
            System.out.println("Checking withdrawal denied: exceeds overdraft limit.");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Checking, Overdraft Limit: " + overdraftLimit);
    }

    @Override
    public double calculateInterest() {
        return 0.0;
    }
}