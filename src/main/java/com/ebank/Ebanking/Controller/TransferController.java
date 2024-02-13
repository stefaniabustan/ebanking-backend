package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.beans.Account;
import com.ebank.Ebanking.Entity.beans.Transfer;
import com.ebank.Ebanking.Service.Interface.AccountService;
import com.ebank.Ebanking.Service.Interface.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
public class TransferController {
    @Autowired
    private TransferService transferService;

    @RequestMapping(value = "/transfer/modify",method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public Transfer modify(@RequestParam Map<String,String> allParams) {
        return transferService.modify(allParams);
    }

    @RequestMapping(value = "/transfer/find")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public List<Transfer> findAllBy(@RequestParam Map<String,String> allParams) {
        return transferService.findBy(allParams);
    }

}
