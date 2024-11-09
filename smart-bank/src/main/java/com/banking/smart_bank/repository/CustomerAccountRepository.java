package com.banking.smart_bank.repository;

import com.banking.smart_bank.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    List<CustomerAccount> findByUserId(Long userId);
}

