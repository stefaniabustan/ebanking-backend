package com.ebank.Ebanking.Service.Interface;

import com.ebank.Ebanking.Entity.beans.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findBy(Map<String, String> allParams);
    User modify(Map<String,String> allParams);

    User getUserByUsername(String username);
     List<User> getAllUsers();
}
