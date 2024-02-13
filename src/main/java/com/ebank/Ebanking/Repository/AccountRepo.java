package com.ebank.Ebanking.Repository;

import com.ebank.Ebanking.Entity.beans.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo   extends JpaRepository<Account,Integer> {
    Account getByNumber(String username);

}