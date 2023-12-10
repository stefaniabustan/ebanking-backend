package com.ebank.Ebanking.Controller;

import com.ebank.Ebanking.Entity.User;
import com.ebank.Ebanking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User postDetails(@RequestBody User user)
    {
        return userService.saveDetails(user);
    }

}
