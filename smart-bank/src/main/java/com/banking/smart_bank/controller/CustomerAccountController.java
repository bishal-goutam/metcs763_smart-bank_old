package com.banking.smart_bank.controller;

import com.banking.smart_bank.dto.CustomerAccountDto;
import com.banking.smart_bank.entity.CustomerAccount;
import com.banking.smart_bank.service.CustomerAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/accounts")
public class CustomerAccountController {

    private CustomerAccountService customerAccountService;

    public CustomerAccountController(CustomerAccountService accountService) {
        this.customerAccountService = accountService;
    }

    // Add-CustomerAccount REST API
    @PostMapping
    public ResponseEntity<CustomerAccountDto> addAccount(@RequestBody CustomerAccountDto accountDto){
        return new ResponseEntity<>(customerAccountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get-CustomerAccount REST API
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAccountDto> getAccountById(@PathVariable Long id){
        CustomerAccountDto customerAccountDto = customerAccountService.getAccountById(id);
        return ResponseEntity.ok(customerAccountDto);
    }

     @PutMapping("/{id}/deposit")
    public ResponseEntity<CustomerAccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        CustomerAccountDto customerAccountDto = customerAccountService.deposit(id, amount);
        return ResponseEntity.ok(customerAccountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<CustomerAccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request){

        double amount = request.get("amount");
        CustomerAccountDto customerAccountDto = customerAccountService.withdraw(id, amount);
        return ResponseEntity.ok(customerAccountDto);
    }


    @GetMapping
    public ResponseEntity<List<CustomerAccountDto>> getAllAccounts(){
        List<CustomerAccountDto> accounts = customerAccountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        customerAccountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }
}
