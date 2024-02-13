package com.ebank.Ebanking.Repository;

import com.ebank.Ebanking.Entity.beans.Client;
import com.ebank.Ebanking.Entity.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo  extends JpaRepository<Client,Integer> {

}
