package com.vitalsport.registration.repository;

import com.vitalsport.registration.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "SELECT * FROM account WHERE (email = ?1 OR nick_name=?1) AND password =?2", nativeQuery = true)
    Account findByEmailAndPasswordOrNickNameAndPassword(String emailOrNickName, String password);

    Account findByEmail(String email);
    Account findByNickName(String nickName);
}
