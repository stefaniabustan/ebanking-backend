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
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
        User pricingStrategy = setPricingStrategy(allParams);
        Example<User> example = Example.of(pricingStrategy);

        return userRepo.findAll(example);

    }
    @Override
    public List<User> modify(Map<String, String> allParams) {
        User user = setPricingStrategy(allParams);
        Example<User> example = Example.of( setPricingStrategy(allParams));//pt a face delete or update trebuie ID ul
        List <User> listUser = Collections.emptyList();
        switch (allParams.get("operation")){
            case "delete":
                listUser = userRepo.findAll(example);
                for(User  u: listUser)
                    userRepo.delete(u);
                break;

            case "update":
//sa vad in functie de cum fac in interfata
                listUser = userRepo.findAll(example);
                user = userRepo.findAll(example).get(0);

                if(this.findBy(allParams).get(0)!=null)
                {
                    User aux= userRepo.findAll(example).get(0); //to take the attributes unchanged
                    if(user.getPassword()==null && aux.getPassword()!=null)
                        user.setPassword(aux.getPassword());
                    if(user.getUsername()==null && aux.getUsername()!=null)
                        user.setUsername(aux.getUsername());
                    if(user.getType()==null && aux.getType()!=null)
                        user.setType(aux.getType());
                    userRepo.save(aux);
                }

                break;

            case "insert":
            {
                try{
                    userRepo.save(user);
                    listUser = Collections.singletonList(user);
                }catch (Exception e)
                {
                    System.out.println("username unic");
                }
            }
        }
        return listUser;
    }

    @Override
    public User getUserByUsername(String username){
        User user=userRepo.getByUsername(username);
        if(user==null) return new User("null","null",UserType.ADMIN, Status.ACTIVE);
        else return user;
    }

    private User setPricingStrategy(Map<String, String> allParams){ //return user
        User user= new User();
        if (allParams.get("id")!=null)
            user.setId(Integer.parseInt(allParams.get("id")));
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


}
