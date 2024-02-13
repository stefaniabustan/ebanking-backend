package com.ebank.Ebanking.Service.Interface;

import com.ebank.Ebanking.Entity.beans.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {
    List<Client> findBy(Map<String, String> allParams);
    List <Client> modify(Map<String,String> allParams);
}
