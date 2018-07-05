package com.twg.exception;

import com.twg.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by twg on 2017/6/26.
 */
/*spring只对RuntimeException作事务回滚，不会对Exception回滚*/
@Getter
public class UserException extends RuntimeException{

    private Integer code;

    private String message;

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}
