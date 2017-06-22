package com.twg.controller;

import com.twg.entity.User;
import com.twg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by twg on 2017/6/22.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users")
    public List<User> userList(){
        return userRepository.findAll();
    }
}
