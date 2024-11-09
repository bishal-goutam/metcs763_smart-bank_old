package com.banking.smart_bank.service;

import com.banking.smart_bank.entity.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByAccountId(Long accountId);
}
