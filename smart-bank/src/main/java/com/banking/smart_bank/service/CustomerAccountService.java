package com.banking.smart_bank.service;

import com.banking.smart_bank.dto.CustomerAccountDto;
import java.util.List;

public interface CustomerAccountService {
    CustomerAccountDto createAccount(CustomerAccountDto customeraccountDto);
    CustomerAccountDto getAccountById(Long id);
    CustomerAccountDto deposit(Long id, double amount);
    CustomerAccountDto withdraw(Long id, double amount);
    List<CustomerAccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
