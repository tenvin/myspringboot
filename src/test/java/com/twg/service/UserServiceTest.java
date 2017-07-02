package com.twg.service;

import com.twg.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by twg on 2017/6/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void findOneTest() throws Exception {
        User user = userService.findOne(6);
        Assert.assertEquals(new Integer(60), user.getAge());
    }

    @Test
    public void findByNameTest() {
        User user = userService.findByUsername("AAA");
        System.out.println(user);
    }

}