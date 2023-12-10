package com.ebank.Ebanking.Service;

import com.ebank.Ebanking.Entity.User;
import com.ebank.Ebanking.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepo userRepo;

    public User saveDetails(User user){

        return userRepo.save(user);
    }
}
