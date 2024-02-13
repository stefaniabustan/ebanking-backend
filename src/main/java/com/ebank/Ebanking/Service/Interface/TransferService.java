package com.ebank.Ebanking.Service.Interface;

import com.ebank.Ebanking.Entity.beans.Transfer;

import java.util.List;
import java.util.Map;

public interface TransferService {
    List<Transfer> findBy(Map<String, String> allParams);
    Transfer modify(Map<String,String> allParams);
}
