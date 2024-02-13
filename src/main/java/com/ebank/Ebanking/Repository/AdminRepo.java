package com.ebank.Ebanking.Repository;

import com.ebank.Ebanking.Entity.beans.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo  extends JpaRepository<Admin,Integer> {

}
