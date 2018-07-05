package com.twg.exception;

import com.twg.VO.Result;
import com.twg.enums.ResultEnum;
import com.twg.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by twg on 2017/6/23.
 */
@ControllerAdvice
@Slf4j
public class UserExceptionHandle {

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof UserException){
            UserException userException = (UserException)e;
            return ResultUtil.error(userException.getCode(),userException.getMessage());
        }else {
            log.error("系统异常{}",e);
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR.getCode(),ResultEnum.UNKNOW_ERROR.getMsg());
        }

    }
}
