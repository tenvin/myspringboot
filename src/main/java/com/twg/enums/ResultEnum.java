package com.twg.enums;

/**
 * Created by twg on 2017/6/26.
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    UNDER_40(100,"小于40"),
    ABOVE_60(101,"大于60"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
