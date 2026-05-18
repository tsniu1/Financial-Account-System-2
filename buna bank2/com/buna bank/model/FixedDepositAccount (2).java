package com.bunabank.model;

public class FixedDepositAccount extends SavingsAccount {
    private int tenureMonths;

    public FixedDepositAccount(String accountNumber, double balance, String accountHolderName,
                               double interestRate, int tenureMonths) {
        super(accountNumber, balance, accountHolderName, interestRate);
        this.tenureMonths = tenureMonths;
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Fixed Deposit: Withdrawal not allowed before maturity (" + tenureMonths + " months).");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Fixed Deposit, Tenure: " + tenureMonths + " months");
    }

}