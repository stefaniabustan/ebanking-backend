package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.beans.Admin;
import com.ebank.Ebanking.Service.Implementation.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/addAdmin")
    public Admin postDetails(@RequestBody Admin admin)
    {
        return adminService.saveDetails(admin);
    }

    @RequestMapping(value = "/admin/modify",method = RequestMethod.POST)
    public List<Admin> modify(@RequestParam Map<String,String> allParams) {
        return adminService.modify(allParams);
    }

    @RequestMapping(value = "/admin/find")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    public List<Admin> findAllBy(@RequestParam Map<String,String> allParams) {
        return adminService.findBy(allParams);
    }

    @RequestMapping(value = "/admin/logOut", method = RequestMethod.GET)
    public void logOut( HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return;

    }

    @RequestMapping(value = "/admin/check", method = RequestMethod.GET)
    public String check( HttpServletRequest request) {
        return (String) request.getSession().getAttribute("admin");
    }

    @RequestMapping(value="/admin/saveAdmin",method = RequestMethod.POST)
    public void savePatient(@RequestBody Admin admin){
        System.out.println(admin.getUserId());
        adminService.saveAdmin(admin);
    }

}
