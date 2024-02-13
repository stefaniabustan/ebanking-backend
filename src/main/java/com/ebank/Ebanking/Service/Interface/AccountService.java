package com.ebank.Ebanking.Service.Interface;

import com.ebank.Ebanking.Entity.beans.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {
    List<Account> findBy(Map<String, String> allParams);
    Account modify(Map<String,String> allParams);
    Account getAccountByNumber(String number);
}
