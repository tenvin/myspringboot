package com.twg.exception;

import com.twg.dto.Result;
import com.twg.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by twg on 2017/6/23.
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof UserException){
            UserException userException = (UserException)e;
            return ResultUtil.error(userException.getCode(),userException.getMessage());
        }else {
            logger.error("系统异常{}",e);
            return ResultUtil.error(-1,"未知异常");
        }

    }
}
