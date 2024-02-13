package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.beans.Employee;
import com.ebank.Ebanking.Service.Implementation.EmployeeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/addEmployee")
    public Employee postDetails(@RequestBody Employee employee)
    {
        return employeeService.saveDetails(employee);
    }

    @RequestMapping(value = "/employee/modify",method = RequestMethod.POST)
    public List<Employee> modify(@RequestParam Map<String,String> allParams) {
        return employeeService.modify(allParams);
    }

    @RequestMapping(value = "/employee/find")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    public List<Employee> findAllBy(@RequestParam Map<String,String> allParams) {
        return employeeService.findBy(allParams);
    }

    @RequestMapping(value = "/employee/logOut", method = RequestMethod.GET)
    public void logOut( HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return;

    }

    @RequestMapping(value = "/employee/check", method = RequestMethod.GET)
    public String check( HttpServletRequest request) {
        return (String) request.getSession().getAttribute("employee");
    }

    @RequestMapping(value="/employee/saveEmployee",method = RequestMethod.POST)
    public void savePatient(@RequestBody Employee employee){
        System.out.println(employee.getUserId());
        employeeService.saveEmployee(employee);
    }

}
