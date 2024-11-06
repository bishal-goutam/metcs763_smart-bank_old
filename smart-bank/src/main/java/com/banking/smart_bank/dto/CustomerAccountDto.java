package com.banking.smart_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerAccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}

