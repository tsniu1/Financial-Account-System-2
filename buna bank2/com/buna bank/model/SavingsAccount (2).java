package com.bunabank.model;

public class SavingsAccount extends Account {
    private double interestRate; 

    public SavingsAccount(String accountNumber, double balance, String accountHolderName, double interestRate) {
        super(accountNumber, balance, accountHolderName);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (getBalance() - amount) >= 500) { // use getter
            super.withdraw(amount);
            System.out.println("Savings withdrawal complete. Interest rate: " + interestRate + "%");
        } else {
            System.out.println("Savings withdrawal denied: would fall below minimum balance of 500.");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Savings, Interest Rate: " + interestRate + "%");
    }

    @Override
    public double calculateInterest() {
        
        return getBalance() * (interestRate / 100);
    }

    public static void displayBankInfo() {
        System.out.println("Buna Bank - Savings Department: Special rates for loyal customers.");
    }
}