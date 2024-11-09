package com.banking.smart_bank.service.impl;

import com.banking.smart_bank.repository.*;

import com.banking.smart_bank.dto.CustomerAccountDto;
import com.banking.smart_bank.entity.CustomerAccount;
import com.banking.smart_bank.mapper.CustomerAccountMapper;
import com.banking.smart_bank.repository.CustomerAccountRepository;
import com.banking.smart_bank.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    public CustomerAccountServiceImpl(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    @Override
    public CustomerAccountDto createAccount(CustomerAccountDto accountDto) {
        CustomerAccount customeraccount = CustomerAccountMapper.mapToAccount(accountDto);
        CustomerAccount savedCustomerAccount = customerAccountRepository.save(customeraccount);
        return CustomerAccountMapper.mapToAccountDto(savedCustomerAccount);
    }

    @Override
    public CustomerAccountDto getAccountById(Long id) {

        CustomerAccount account = customerAccountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
        return CustomerAccountMapper.mapToAccountDto(account);
    }

    @Override
    public CustomerAccountDto deposit(Long id, double amount) {

        CustomerAccount account = customerAccountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        CustomerAccount savedAccount = customerAccountRepository.save(account);
        return CustomerAccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public CustomerAccountDto withdraw(Long id, double amount) {

        CustomerAccount customeraccount = customerAccountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        if(customeraccount.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = customeraccount.getBalance() - amount;
        customeraccount.setBalance(total);
        CustomerAccount savedAccount = customerAccountRepository.save(customeraccount);

        return CustomerAccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<CustomerAccountDto> getAllAccounts() {
        List<CustomerAccount> accounts = customerAccountRepository.findAll();
        return accounts.stream().map((account) -> CustomerAccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        CustomerAccount account = customerAccountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
        customerAccountRepository.deleteById(id);
    }

    @Override
    public List<CustomerAccountDto> getAccountsByUser(Long userId) {
        List<CustomerAccount> accounts = customerAccountRepository.findByUserId(userId);
        return accounts.stream()
                .map(CustomerAccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

}
