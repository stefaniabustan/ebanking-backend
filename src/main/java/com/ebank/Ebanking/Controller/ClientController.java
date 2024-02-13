package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.beans.Client;
import com.ebank.Ebanking.Service.Implementation.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping("/addClient")
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public Client postDetails(@RequestBody Client client)
    {
        return clientService.saveDetails(client);
    }

    @RequestMapping(value = "/client/modify",method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public List<Client> modify(@RequestParam Map<String,String> allParams) {
        return clientService.modify(allParams);
    }

    @RequestMapping(value = "/client/find")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public List<Client> findAllBy(@RequestParam Map<String,String> allParams) {
        return clientService.findBy(allParams);
    }

    @RequestMapping(value = "/client/findOne")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public Client findOne(@RequestParam Map<String,String> allParams) {
        return clientService.findBy(allParams).get(0);
    }


    @RequestMapping(value = "/client/logOut", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public void logOut( HttpServletRequest request) {
        request.getSession().removeAttribute("client");
        return;

    }

    @RequestMapping(value = "/client/check", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public String check( HttpServletRequest request) {
        return (String) request.getSession().getAttribute("client");
    }



    @RequestMapping(value="/client/saveClient",method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public void savePatient(@RequestBody Client client){
        System.out.println(client.getUserId());
        clientService.saveClient(client);
    }

}
