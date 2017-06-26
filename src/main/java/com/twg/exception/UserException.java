package com.twg.exception;

import com.twg.enums.ResultEnum;

/**
 * Created by twg on 2017/6/26.
 */
/*spring只对RuntimeException作事务回滚，不会对Exception回滚*/
public class UserException extends RuntimeException{

    private Integer code;


    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
