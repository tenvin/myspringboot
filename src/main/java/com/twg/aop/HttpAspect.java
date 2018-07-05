package com.twg.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by twg on 2017/6/22.
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.twg.controller.UserController.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        log.info("before");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("url={}",request.getRequestURL());

        log.info("method={}",request.getMethod());

        log.info("ip={}",request.getRemoteAddr());

        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName()
                +"."+joinPoint.getSignature().getName());

        log.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        log.info("after");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        log.info("response={}",object.toString());
    }

}
