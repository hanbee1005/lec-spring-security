package com.example.lecspringsecurity.repository;

import com.example.lecspringsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
