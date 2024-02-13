package com.ebank.Ebanking.Repository;

import com.ebank.Ebanking.Entity.beans.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepo   extends JpaRepository<Transfer,Integer> {

}