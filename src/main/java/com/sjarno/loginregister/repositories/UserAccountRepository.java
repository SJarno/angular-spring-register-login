package com.sjarno.loginregister.repositories;

import java.util.Optional;

import com.sjarno.loginregister.models.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
    Optional<UserAccount> findByUsername(String username);
}
