package com.twg.controller;

import com.twg.VO.Result;
import com.twg.entity.User;
import com.twg.service.UserService;
import com.twg.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by twg on 2017/6/22.
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public Result<User> findAll(){
        List<User> userList =  userService.findAll();
        return ResultUtil.success(userList);
    }
    /**
     * 增加一个用户
     */
    @PostMapping(value = "/users")
    public Result<User> userAdd(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

        user.setName(user.getName());
        user.setAge(user.getAge());

        return ResultUtil.success(userService.save(user));
    }

    @GetMapping(value = "/users/{id}")
    public Result getUser(@PathVariable("id") Long id) {
        return ResultUtil.success(userService.findOne(id));
    }

    @PostMapping(value = "/users/set")
    public Result setUsesr(@RequestParam("name") String name,
                         @RequestParam("age") Integer age){
        User user = new User(name,age);
        return ResultUtil.success(userService.save(user));
    }

}
