package com.banking.smart_bank.controller;

import com.banking.smart_bank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/internal")
    public ResponseEntity<String> internalTransfer(@RequestBody Map<String, Object> transferData) {
        Long fromAccountId = Long.parseLong(transferData.get("fromAccountId").toString());

        Long toAccountId = Long.parseLong(transferData.get("toAccountId").toString());
        double amount = Double.parseDouble(transferData.get("amount").toString());
        String result = transferService.internalTransfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/external")
    public ResponseEntity<String> externalTransfer(@RequestBody Map<String, Object> transferData) {
        Long fromAccountId = Long.parseLong(transferData.get("fromAccountId").toString());
        double amount = Double.parseDouble(transferData.get("amount").toString());
        String toRoutingNumber = transferData.get("toRoutingNumber").toString();
        String toAccountNumber = transferData.get("toAccountNumber").toString();
        String result = transferService.externalTransfer(fromAccountId, amount, toRoutingNumber, toAccountNumber);
        return ResponseEntity.ok(result);
    }
}

