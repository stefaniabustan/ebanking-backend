package com.ebank.Ebanking.Repository;

import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface UserRepo extends JpaRepository<User,Integer> {
    public List<User> findById(int id);
    public List<User> findByUsername(String name);
    public List<User> findByPassword(String pass);
    public List<User> findByType(UserType type);


    User getByUsername(String username);
}
