package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.beans.Account;

import com.ebank.Ebanking.Service.Interface.AccountService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/account/modify",method = RequestMethod.POST)
    public Account modify(@RequestParam Map<String,String> allParams) {
        return accountService.modify(allParams);
    }

    @RequestMapping(value = "/account/find")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    public List<Account> findAllBy(@RequestParam Map<String,String> allParams) {
        return accountService.findBy(allParams);
    }

    @RequestMapping(value="/getAccount/{number}",method= RequestMethod.GET)
    public Account getAccountByNumber(@PathVariable String number){
        return accountService.getAccountByNumber(number);
    }

}
