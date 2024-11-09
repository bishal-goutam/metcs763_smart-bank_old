package com.banking.smart_bank.mapper;

import com.banking.smart_bank.dto.CustomerAccountDto;
import com.banking.smart_bank.entity.CustomerAccount;

public class CustomerAccountMapper {

    public static CustomerAccountDto mapToAccountDto(CustomerAccount account) {
        return new CustomerAccountDto(
                account.getId(),
                account.getAccountHolderName(),  // Get name from the User entity
                account.getBalance()
        );
    }

    public static CustomerAccount mapToAccount(CustomerAccountDto accountDto) {
        CustomerAccount account = new CustomerAccount();
        account.setId(accountDto.getId());
        account.setBalance(accountDto.getBalance());
        // We assume user is set elsewhere; `accountHolderName` is not stored in the entity directly
        return account;
    }
    }

