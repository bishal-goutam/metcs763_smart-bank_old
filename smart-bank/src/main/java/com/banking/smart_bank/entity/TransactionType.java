package com.banking.smart_bank.entity;

public enum TransactionType {
    credit, debit

    /* public static TransactionType fromString(String type) {
        try {
            return TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid transaction type: " + type);
        }
    }*/
}
