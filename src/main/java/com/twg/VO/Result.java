package com.twg.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by twg on 2017/6/23.
 */
@Data
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;
}
