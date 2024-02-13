package com.ebank.Ebanking.Service.Implementation;

import com.ebank.Ebanking.Entity.beans.Client;
import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Entity.enums.Status;
import com.ebank.Ebanking.Repository.ClientRepo;
import com.ebank.Ebanking.Service.Interface.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService, Serializable {

    @Autowired
    private ClientRepo clientRepo;

    public Client saveDetails(Client client){

        return clientRepo.save(client);
    }
    public void saveClient(Client client){
        clientRepo.save(client);
    }


    @Override
    public List<Client> findBy(Map<String, String> allParams) {
        //asa imi cauta mereu si dupa id
        Client pricingStrategy = setPricingStrategy(allParams,2);
        Example<Client> example = Example.of(pricingStrategy);

        return clientRepo.findAll(example);

    }
    @Override
    public List<Client> modify(Map<String, String> allParams) {
        Client client = setPricingStrategy(allParams,2);
        Example<Client> example = Example.of( setPricingStrategy(allParams,1));//pt a face delete or update trebuie ID ul
        List <Client> listClient = Collections.emptyList();
        if(allParams.get("operation")!=null)
        {
            switch (allParams.get("operation")){
                case "delete":
                    listClient = clientRepo.findAll(example);
                    for(Client  u: listClient)
                        clientRepo.delete(u);
                    break;

                case "update":
                {
                    //sa vad in functie de cum fac in interfata
                    Client old = clientRepo.findAll(example).get(0);
                    if(client.getFirstname()==null && old.getFirstname()!=null)
                        client.setFirstname(old.getFirstname());
                    if(client.getSecondname()==null && old.getSecondname()!=null)
                        client.setSecondname(old.getSecondname());
                    if(client.getAddress()==null && old.getAddress()!=null)
                        client.setAddress(old.getAddress());
                    if(client.getUserId()==null && old.getUserId()!=null)
                        client.setUserId(old.getUserId());

                    clientRepo.save(client);

                    break;
                }
                case "insert":
                {
                    try{
                        clientRepo.save(client);
                        listClient = Collections.singletonList(client);
                        System.out.println("face insert client");
                    }catch (Exception e)
                    {
                        System.out.println("username unic");
                    }

                }
            }
        }

        return listClient;
    }

    private Client setPricingStrategy(Map<String, String> allParams, int step){ //return Client
        Client client= new Client();
        if (allParams.get("id")!=null)
           client.setId(Integer.parseInt(allParams.get("id")));
        if(step==1)
            return  client;
        if (allParams.get("firstname")!=null)
           client.setFirstname(allParams.get("firstname"));
        if (allParams.get("secondname")!=null)
            client.setSecondname(allParams.get("secondname"));
        if (allParams.get("address")!=null)
            client.setAddress(allParams.get("address"));
        if (allParams.get("email")!=null)
            client.setEmail(allParams.get("email"));
        if(allParams.get("user_id")!=null)
        {
            User user=new User();
            user.setId(Integer.valueOf(allParams.get("user_id")));
            client.setUserId(user);
            System.out.println("sunt aici");

        }
        return client;
    }
}
