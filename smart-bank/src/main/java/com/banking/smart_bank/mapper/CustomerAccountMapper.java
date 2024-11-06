package com.banking.smart_bank.mapper;

import com.banking.smart_bank.dto.CustomerAccountDto;
import com.banking.smart_bank.entity.CustomerAccount;

public class CustomerAccountMapper {

    public static CustomerAccount mapToAccount(CustomerAccountDto customeraccountDto){
        CustomerAccount customeraccount = new CustomerAccount(
                customeraccountDto.getId(),
                customeraccountDto.getAccountHolderName(),
                customeraccountDto.getBalance()
        );

        return customeraccount;
    }

    public static CustomerAccountDto mapToAccountDto(CustomerAccount customeraccount){
        CustomerAccountDto customeraccountDto = new CustomerAccountDto(
                customeraccount.getId(),
                customeraccount.getAccountHolderName(),
                customeraccount.getBalance()
        );
        return customeraccountDto;
    }
}
