package com.banking.smart_bank.repository;

import com.banking.smart_bank.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
}

