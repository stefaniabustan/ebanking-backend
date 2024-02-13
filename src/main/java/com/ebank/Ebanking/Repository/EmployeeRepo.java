package com.ebank.Ebanking.Repository;

import com.ebank.Ebanking.Entity.beans.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepo  extends JpaRepository<Employee,Integer> {

}
