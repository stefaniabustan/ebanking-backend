package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.beans.User;
import com.ebank.Ebanking.Service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")//pt ca front ruleaza pe alt server
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/addUser")
    public User postDetails(@RequestBody User user)
    {
        return userService.saveDetails(user);
    }

    @RequestMapping(value = "/user/modify",method = RequestMethod.POST)
    public User modify(@RequestParam Map<String,String> allParams) {
        return userService.modify(allParams);
    }

    @RequestMapping(value = "/user/find")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    public List<User> findAllBy(@RequestParam Map<String,String> allParams) {
        return userService.findBy(allParams);
    }

    @RequestMapping(value = "/user/findByUsername")// nu mai am nevoie pt ca nu folosesc body in postman: method = RequestMethod.POST)
    public User findByUsername(@RequestParam Map<String,String> allParams) {
        return userService.findBy(allParams).get(0);
    }

    @RequestMapping(value="/getUser/{username}",method= RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value="/user/allUsers",method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
