package com.ebank.Ebanking.Service.Interface;

import com.ebank.Ebanking.Entity.beans.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findBy(Map<String, String> allParams);
    List <Employee> modify(Map<String,String> allParams);
}
