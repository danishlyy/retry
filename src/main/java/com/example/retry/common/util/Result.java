package com.example.retry.common.util;

import com.example.retry.common.enums.ResponseEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 15:07
 */
@Getter
@Setter
public class Result<T> {

    private String code;

    private String msg;

    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(ResponseEnum.SUCCESS.getValue());
        result.setMsg(ResponseEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failOfCode(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
