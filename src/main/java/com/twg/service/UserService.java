package com.twg.service;

import com.twg.VO.Result;
import com.twg.entity.User;
import com.twg.enums.ResultEnum;
import com.twg.exception.UserException;
import com.twg.repository.UserRepository;
import com.twg.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by twg on 2017/6/22.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getAge(Long id) throws Exception {
        User user = userRepository.findOne(id);
        Integer age = user.getAge();

        if(age>60){
            throw new UserException(ResultEnum.ABOVE_60);
        }else if(age<40){
            throw new UserException(ResultEnum.UNDER_40);
        }
        return user;
    }

    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    public User save(User user){return userRepository.save(user);}
}
