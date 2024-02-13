package com.ebank.Ebanking.Service.Implementation;

import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Entity.enums.Status;
import com.ebank.Ebanking.Entity.enums.UserType;
import com.ebank.Ebanking.Repository.UserRepo;
import com.ebank.Ebanking.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.awt.event.TextEvent;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.ebank.Ebanking.Entity.enums.UserType.ADMIN;

@Service
public class UserServiceImpl implements UserService, Serializable {

    @Autowired
    private UserRepo userRepo;


    public User saveDetails(User user){

        return userRepo.save(user);
    }

    @Override
    public List<User> findBy(Map<String, String> allParams) {
        //asa imi cauta mereu si dupa id
        User pricingStrategy = setPricingStrategy(allParams,2);
        Example<User> example = Example.of(pricingStrategy);

        return userRepo.findAll(example);

    }
    @Override
    public User modify(Map<String, String> allParams) {
        User user = setPricingStrategy(allParams,2);
        Example<User> example = Example.of( setPricingStrategy(allParams,1));//pt a face delete or update trebuie ID ul
        if(allParams.get("operation")!=null)
        {
            switch (allParams.get("operation")){
                case "delete":
                    user = userRepo.findAll(example).get(0);
                    userRepo.delete(user);
                    break;


                case "update": //neaparat id sa dau (altfel face insert)
                {
                    User old = userRepo.findAll(example).get(0);
                    if(user.getUsername()==null && old.getUsername()!=null)
                        user.setUsername(old.getUsername());
                    if(user.getPassword()==null && old.getPassword()!=null)
                        user.setPassword(old.getPassword());
                    userRepo.save(user);
                    System.out.println("fac update user");
                    break;
                }

                case "insert":
                {
                    try{
                        userRepo.save(user);
                        System.out.println("face insert user");
                    }catch (Exception e)
                    {
                        System.out.println("username unic");
                    }
                }
            }
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username){
        User user=userRepo.getByUsername(username);
        if(user==null) return new User("null","null", ADMIN, Status.ACTIVE);
        else return user;
    }

    private User setPricingStrategy(Map<String, String> allParams, int step){ //return user
        User user= new User();
        if (allParams.get("id")!=null)
            user.setId(Integer.parseInt(allParams.get("id")));
        if(step==1)
            return user;
        if (allParams.get("username")!=null)
            user.setUsername(allParams.get("username"));
        if (allParams.get("password")!=null)
            user.setPassword(allParams.get("password"));
        if (allParams.get("type")!=null)
            user.setType(UserType.valueOf(allParams.get("type")));
        if (allParams.get("status")!=null)
            user.setStatus(Status.valueOf(allParams.get("status")));
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
