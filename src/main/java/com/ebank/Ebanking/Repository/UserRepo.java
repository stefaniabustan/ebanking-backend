package com.ebank.Ebanking.Repository;

import com.ebank.Ebanking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
