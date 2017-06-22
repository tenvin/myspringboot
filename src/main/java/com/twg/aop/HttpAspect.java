package com.twg.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * Created by twg on 2017/6/22.
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);

//    @Before("execution(public * com.twg.controller.UserController.userList(..))")
//    public void log(){
//        System.out.println("1111");
//    }

    @Pointcut("execution(public * com.twg.controller.UserController.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(){
        logger.info("before");
    }

    @After("log()")
    public void doAfter(){
        logger.info("after");
    }

}
