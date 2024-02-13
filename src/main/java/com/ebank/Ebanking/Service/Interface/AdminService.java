package com.ebank.Ebanking.Service.Interface;

import com.ebank.Ebanking.Entity.beans.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Admin> findBy(Map<String, String> allParams);
    List <Admin> modify(Map<String,String> allParams);
}
