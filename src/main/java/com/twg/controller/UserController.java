package com.twg.controller;

import com.twg.dto.Result;
import com.twg.entity.User;
import com.twg.enums.ResultEnum;
import com.twg.exception.UserException;
import com.twg.repository.UserRepository;
import com.twg.service.UserService;
import com.twg.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by twg on 2017/6/22.
 */
@RestController
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> userList(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/users")
    public Result<User> userAdd(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

        user.setName(user.getName());
        user.setAge(user.getAge());

        return ResultUtil.success(userRepository.save(user));
    }

    @GetMapping(value = "/users/getAge/{id}")
    public void getAge(@PathVariable("id") Long id) throws Exception {
        User user = userRepository.findOne(id);
        Integer age = user.getAge();

        if(age>60){
            throw new UserException(ResultEnum.ABOVE_60);
        }else if(age<40){
            throw new UserException(ResultEnum.UNDER_40);
        }


    }
}
