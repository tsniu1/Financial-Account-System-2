package com.bunabank.model;

public final class TransactionLogger {
    private static int transactionCount = 0;

    public static void logTransaction(String message) {
        transactionCount++;
        System.out.println("[LOG #" + transactionCount + "] " + message);
    }
}