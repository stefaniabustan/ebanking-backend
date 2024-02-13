package com.ebank.Ebanking.Service.Implementation;


import com.ebank.Ebanking.Entity.beans.Account;
import com.ebank.Ebanking.Entity.beans.Client;
import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Entity.enums.Status;
import com.ebank.Ebanking.Entity.enums.TypeMoneda;
import com.ebank.Ebanking.Repository.AccountRepo;
import com.ebank.Ebanking.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.ebank.Ebanking.Entity.enums.UserType.ADMIN;

@Service
public class AccountServiceImpl implements AccountService, Serializable {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Account> findBy(Map<String, String> allParams) {
        Account pricingStrategy = setPricingStrategy(allParams,2);
        Example<Account> example = Example.of(pricingStrategy);
        return accountRepo.findAll(example);
    }

    @Override
    public Account getAccountByNumber(String number){
        Account account=accountRepo.getByNumber(number);
        if(account==null) return new Account(null,"null" ,Float.valueOf("0.0"),"null",TypeMoneda.RON);
        else return account;
    }

    @Override
    public Account modify(Map<String, String> allParams) {
        Account account = setPricingStrategy(allParams,2);
        Example<Account> example = Example.of( setPricingStrategy(allParams,1));//pt a face delete or update trebuie ID ul
        if(allParams.get("operation")!=null)
        {
            switch (allParams.get("operation")){
                case "delete":
                    account = accountRepo.findAll(example).get(0);
                    accountRepo.delete(account);
                    break;

                case "update": //neaparat id sa dau (altfel face insert)
                {
                    Account old = accountRepo.findAll(example).get(0);
                    if(account.getDescription()==null && old.getDescription()!=null)
                        account.setDescription(old.getDescription());
                    if(account.getBalance()==null && old.getBalance()!=null)
                        account.setBalance(old.getBalance());
                    if(account.getMoneda()==null && old.getMoneda()!=null)
                        account.setMoneda(old.getMoneda());
                    else {
                        //s-a schimbat moneda
                        //convert
                        if(account.getMoneda()==TypeMoneda.RON)
                        {
                            //convert din euro in ron
                            account.setBalance((float) (account.getBalance()*4.97));
                        }
                        else
                        {
                            account.setBalance((float) (account.getBalance()/4.97));
                        }
                    }
                    if(account.getClientId()==null && old.getClientId()!=null)
                        account.setClientId(old.getClientId());
                    if(account.getNumber()==null && old.getNumber()!=null)
                        account.setNumber(old.getNumber());
                    accountRepo.save(account);
                    System.out.println("fac update account");
                    break;
                }
                case "insert":
                {
                    try{
                        accountRepo.save(account);
                        System.out.println("face insert account");
                    }catch (Exception e)
                    {
                        System.out.println("number unic");
                    }

                }
            }
        }

        return account;
    }

    private Account setPricingStrategy(Map<String, String> allParams, int step){
        Account account= new Account();
        if (allParams.get("id")!=null)
            account.setId(Integer.parseInt(allParams.get("id")));
        if(step==1)
            return account;
        if (allParams.get("balance")!=null){
            account.setBalance(Float.valueOf(allParams.get("balance")));
        }
        if (allParams.get("number")!=null)
            account.setNumber(allParams.get("number"));
        if (allParams.get("description")!=null)
            account.setDescription(allParams.get("description"));
        if (allParams.get("moneda")!=null)
            account.setMoneda(TypeMoneda.valueOf(allParams.get("moneda")));

        if(allParams.get("client_id")!=null)
        {
            Client client=new Client();
            client.setId(Integer.valueOf(allParams.get("client_id")));
            account.setClientId(client);
        }
        return account;
    }
}
