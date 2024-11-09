package com.banking.smart_bank.controller;

import com.banking.smart_bank.dto.CustomerAccountDto;
import com.banking.smart_bank.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<CustomerAccountDto>> testGetAllAccounts() {
        List<CustomerAccountDto> accounts = customerAccountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
