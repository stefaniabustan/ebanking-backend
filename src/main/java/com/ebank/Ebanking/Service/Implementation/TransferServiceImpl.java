package com.ebank.Ebanking.Service.Implementation;


import com.ebank.Ebanking.Entity.beans.Account;
import com.ebank.Ebanking.Entity.beans.Transfer;
import com.ebank.Ebanking.Entity.enums.TransferStatus;
import com.ebank.Ebanking.Entity.enums.TypeMoneda;
import com.ebank.Ebanking.Repository.TransferRepo;
import com.ebank.Ebanking.Service.Interface.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class TransferServiceImpl implements TransferService, Serializable {
    @Autowired
    private TransferRepo transferRepo;



    @Override
    public List<Transfer> findBy(Map<String, String> allParams) {
        //asa imi cauta mereu si dupa id
        Transfer pricingStrategy = setPricingStrategy(allParams,2);
        Example<Transfer> example = Example.of(pricingStrategy);

        return transferRepo.findAll(example);

    }
    @Override
    public Transfer modify(Map<String, String> allParams) {
        Transfer transfer = setPricingStrategy(allParams,2);
        Example<Transfer> example = Example.of( setPricingStrategy(allParams,1));//pt a face delete or update trebuie ID ul
        if(allParams.get("operation")!=null)
        {
            switch (allParams.get("operation")){
                case "delete":
                    transfer = transferRepo.findAll(example).get(0);
                    transferRepo.delete(transfer);
                    break;

                case "update": //neaparat id sa dau (altfel face insert)
                {
                    Transfer old = transferRepo.findAll(example).get(0);
                    if(transfer.getDescription()==null && old.getDescription()!=null)
                        transfer.setDescription(old.getDescription());
                    if(transfer.getStatus()==null && old.getStatus()!=null)
                        transfer.setStatus(old.getStatus());
                    if(transfer.getMoneda()==null && old.getMoneda()!=null)
                        transfer.setMoneda(old.getMoneda());
                    if(transfer.getValue()==null && old.getValue()!=null)
                        transfer.setValue(old.getValue());
                    if(transfer.getIdAccountBenef()==null && old.getIdAccountBenef()!=null)
                        transfer.setIdAccountBenef(old.getIdAccountBenef());
                    if(transfer.getIdAccountDistrib()==null && old.getIdAccountDistrib()!=null)
                        transfer.setIdAccountDistrib(old.getIdAccountDistrib());
                    transferRepo.save(transfer);
                    System.out.println("fac update transfer");
                    break;
                }
                case "insert":
                {
                    if(check(transfer))
                    {
                        transferRepo.save(transfer);
                        System.out.println("face insert transfer");
                    }

                }
            }
        }

        return transfer;
    }

    private Boolean check(Transfer transfer)
    {
//        if(transfer.getIdAccountDistrib().getBalance()-transfer.getValue()<0)
//        {
//            return false; //nu are sufic bani
//        }
        return true;
    }

    private Transfer setPricingStrategy(Map<String, String> allParams, int step){
        Transfer transfer= new Transfer();
        if (allParams.get("id")!=null)
            transfer.setId(Integer.parseInt(allParams.get("id")));
        if(step==1)
            return transfer;
        if(allParams.get("id_account_benef")!=null)
        {
            Account account=new Account();
            account.setId(Integer.valueOf(allParams.get("id_account_benef")));
            transfer.setIdAccountBenef(account);
        }
        if(allParams.get("id_account_distrib")!=null)
        {
            Account account=new Account();
            account.setId(Integer.valueOf(allParams.get("id_account_distrib")));
            transfer.setIdAccountDistrib(account);
        }



        if (allParams.get("value")!=null)
            transfer.setValue(Float.valueOf(allParams.get("value")));
        if (allParams.get("description")!=null)
            transfer.setDescription(allParams.get("description"));
        if (allParams.get("moneda")!=null)
            transfer.setMoneda(TypeMoneda.valueOf(allParams.get("moneda")));
        if (allParams.get("status")!=null)
            transfer.setStatus(TransferStatus.valueOf(allParams.get("status")));

        return transfer;
    }
}

