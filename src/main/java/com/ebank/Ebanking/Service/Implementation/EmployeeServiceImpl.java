package com.ebank.Ebanking.Service.Implementation;


import com.ebank.Ebanking.Entity.beans.Employee;
import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Repository.EmployeeRepo;
import com.ebank.Ebanking.Service.Interface.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Service
public class EmployeeServiceImpl implements EmployeeService, Serializable {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee saveDetails(Employee employee){

        return employeeRepo.save(employee);
    }
    public void saveEmployee(Employee employee){
        employeeRepo.save(employee);
    }


    @Override
    public List<Employee> findBy(Map<String, String> allParams) {
        //asa imi cauta mereu si dupa id
        Employee pricingStrategy = setPricingStrategy(allParams);
        Example<Employee> example = Example.of(pricingStrategy);

        return employeeRepo.findAll(example);

    }
    @Override
    public List<Employee> modify(Map<String, String> allParams) {
        Employee employee = setPricingStrategy(allParams);
        Example<Employee> example = Example.of( setPricingStrategy(allParams));//pt a face delete or update trebuie ID ul
        List <Employee> listEmployee = Collections.emptyList();
        if(allParams.get("operation")!=null)
        {
            switch (allParams.get("operation")){
                case "delete":
                    listEmployee = employeeRepo.findAll(example);
                    for(Employee  u: listEmployee)
                    {
                        employeeRepo.delete(u);
                    }

                    break;

                case "update": //neaparat id sa dau (altfel face insert)
                    listEmployee = employeeRepo.findAll(example);
                    employeeRepo.save(employee);

                    break;

                case "insert":
                {
                    try{
                        employeeRepo.save(employee);
                        listEmployee = Collections.singletonList(employee);
                        System.out.println("face insert employee");
                    }catch (Exception e)
                    {
                        System.out.println("username unic");
                    }

                }
            }
        }

        return listEmployee;
    }

    private Employee setPricingStrategy(Map<String, String> allParams){ //return Employee
        Employee employee= new Employee();
        if (allParams.get("id")!=null)
            employee.setId(Integer.parseInt(allParams.get("id")));
        if(allParams.get("user_id")!=null)
        {
            User user=new User();
            user.setId(Integer.valueOf(allParams.get("user_id")));
            employee.setUserId(user);
            System.out.println("sunt aici");

        }
        return employee;
    }
}