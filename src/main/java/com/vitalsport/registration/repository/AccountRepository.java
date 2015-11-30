package com.vitalsport.registration.repository;

import com.vitalsport.registration.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmailAndPassword(String email, String password);
}
