package com.twg.service;

import com.twg.entity.User;
import com.twg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by twg on 2017/6/22.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void insertTwo(){

    }

    public void getAge(Long id) throws Exception {
        User user = userRepository.findOne(id);
        Integer age = user.getAge();

        if(age>60){
            throw new Exception("您已退休");
        }
    }

    public User findOne(long id) {
        return userRepository.findOne(id);
    }
}
