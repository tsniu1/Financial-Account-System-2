package com.bunabank.audit;

import com.bunabank.model.Account;


public class ExternalAudit extends Account {
    public ExternalAudit(String accountNumber, double balance, String accountHolderName) {
        super(accountNumber, balance, accountHolderName);
    }

    public void auditAccount() {
        System.out.println("Auditing account: " + getAccountNumber() +
                           ", Balance: " + getBalance() +
                           ", Holder: " + getAccountHolderName());
    }

    @Override
    public double calculateInterest() {
        return 0.0; 
    }
}