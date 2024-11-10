package com.banking.smart_bank.service;

import com.banking.smart_bank.entity.CustomerAccount;
import com.banking.smart_bank.entity.*;
import com.banking.smart_bank.repository.CustomerAccountRepository;
import com.banking.smart_bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransferService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public String internalTransfer(Long fromAccountId, Long toAccountId, double amount) {
        CustomerAccount fromAccount = customerAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        CustomerAccount toAccount = customerAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (fromAccount.getBalance()<amount) {
            throw new RuntimeException("Insufficient balance in the from account");
        }

        // Deduct from the sender account and add to the receiver account
        double frombalanceBefore = fromAccount.getBalance();
        double frombalanceAfter = fromAccount.getBalance() - amount;
        fromAccount.setBalance(frombalanceAfter);
        double tobalanceBefore = toAccount.getBalance();
        double tobalanceAfter = toAccount.getBalance()+amount;
        toAccount.setBalance(tobalanceAfter);

        // Save updated balances
        customerAccountRepository.save(fromAccount);
        customerAccountRepository.save(toAccount);


        // Log the transaction for both accounts
        Transaction fromTransaction = new Transaction(fromAccount, LocalDateTime.now(),"Internal Transfer to Account " + toAccountId, amount, TransactionType.debit, frombalanceBefore, frombalanceAfter);
        Transaction toTransaction = new Transaction(toAccount, LocalDateTime.now(),"Internal Transfer from Account " + fromAccountId, amount, TransactionType.credit, tobalanceBefore, tobalanceAfter);

        transactionRepository.save(fromTransaction);
        transactionRepository.save(toTransaction);

        return "Transfer successful";
    }

    @Transactional
    public String externalTransfer(Long fromAccountId, double amount, String toRoutingNumber, String toAccountNumber) {
        CustomerAccount fromAccount = customerAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From account not found"));

        if (fromAccount.getBalance()<amount) {
            throw new RuntimeException("Insufficient balance in the from account");
        }

        // Deduct from the sender account
        double balanceBefore = fromAccount.getBalance();
        double balanceAfter = fromAccount.getBalance()-amount;
        fromAccount.setBalance(balanceAfter);
        customerAccountRepository.save(fromAccount);

        // Log the transaction for both accounts
        Transaction fromTransaction = new Transaction(fromAccount, LocalDateTime.now(),"External Transfer to Account " + toAccountNumber, amount,TransactionType.debit, balanceBefore, balanceAfter);

        transactionRepository.save(fromTransaction);

        return "External transfer submitted";
    }

}

