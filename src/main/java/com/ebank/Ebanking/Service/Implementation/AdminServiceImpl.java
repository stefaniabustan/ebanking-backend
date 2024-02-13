package com.ebank.Ebanking.Service.Implementation;

import com.ebank.Ebanking.Entity.beans.Admin;
import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Repository.AdminRepo;
import com.ebank.Ebanking.Service.Interface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ebank.Ebanking.Entity.enums.UserType.ADMIN;


@Service
public class AdminServiceImpl implements AdminService, Serializable {

    @Autowired
    private AdminRepo adminRepo;

    public Admin saveDetails(Admin admin){

        return adminRepo.save(admin);
    }
    public void saveAdmin(Admin admin){
        adminRepo.save(admin);
    }


    @Override
    public List<Admin> findBy(Map<String, String> allParams) {
        //asa imi cauta mereu si dupa id
        Admin pricingStrategy = setPricingStrategy(allParams);
        Example<Admin> example = Example.of(pricingStrategy);

        return adminRepo.findAll(example);

    }
    @Override
    public List<Admin> modify(Map<String, String> allParams) {
        Admin admin = setPricingStrategy(allParams);
        Example<Admin> example = Example.of( setPricingStrategy(allParams));//pt a face delete or update trebuie ID ul
        List <Admin> listAdmin = Collections.emptyList();
        if(allParams.get("operation")!=null)
        {
            switch (allParams.get("operation")){
                case "delete":
                    listAdmin = adminRepo.findAll(example);
                    for(Admin  u: listAdmin)
                    {
                        adminRepo.delete(u);
                    }

                    break;

                case "update": //neaparat id sa dau (altfel face insert)
                    listAdmin = adminRepo.findAll(example);
                    adminRepo.save(admin);

                    break;

                case "insert":
                {
                    try{
                        adminRepo.save(admin);
                        listAdmin = Collections.singletonList(admin);
                        System.out.println("face insert admin");
                    }catch (Exception e)
                    {
                        System.out.println("username unic");
                    }

                }
            }
        }

        return listAdmin;
    }

    private Admin setPricingStrategy(Map<String, String> allParams){ //return Admin
        Admin admin= new Admin();
        if (allParams.get("id")!=null)
            admin.setId(Integer.parseInt(allParams.get("id")));
        if(allParams.get("user_id")!=null)
        {
            User user=new User();
            user.setId(Integer.valueOf(allParams.get("user_id")));
            admin.setUserId(user);
            System.out.println("sunt aici");

        }
        return admin;
    }
}