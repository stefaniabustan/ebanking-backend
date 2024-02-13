package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Entity.enums.Status;
import com.ebank.Ebanking.Service.Implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/addUser")
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public User postDetails(@RequestBody User user)
    {
        return userService.saveDetails(user);
    }

    @RequestMapping(value = "/user/modify",method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public User modify(@RequestParam Map<String,String> allParams) {
        return userService.modify(allParams);
    }

    @RequestMapping(value = "/user/find")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public List<User> findAllBy(@RequestParam Map<String,String> allParams) {
        return userService.findBy(allParams);
    }

    @RequestMapping(value = "/user/findByUsername")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public User findByUsername(@RequestParam Map<String,String> allParams) {
        return userService.findBy(allParams).get(0);
    }

    @RequestMapping(value = "/user/logIn", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
    public User logIn(@RequestParam Map<String,String> allParams, HttpServletRequest request) {
        request.getSession();
        User user = userService.findBy(allParams).get(0);
        if (user.getStatus().equals(Status.ACTIVE)) {
            request.getSession().setAttribute("user ", user.getUsername());//ca sa am numele user ului conectat in front
            return user;
        }
        else
            return null;

    }
    @RequestMapping(value = "/user/logOut", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public void logOut( HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return;

    }

    @RequestMapping(value = "/user/check", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public String check( HttpServletRequest request) {
        return (String) request.getSession().getAttribute("user");
    }

    @RequestMapping(value="/getUser/{username}",method= RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value="/user/allUsers",method= RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
