package com.twg.controller;

import com.twg.entity.User;
import com.twg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
@Slf4j
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public String userList(Model model){
        List<User> userList =  userService.findAll();
        model.addAttribute("users",userList);
        return "users";
    }
}
