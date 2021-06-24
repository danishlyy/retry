package com.example.retry.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 15:22
 */
@Getter
@Setter
@ToString
public class BusinessException extends RuntimeException{


    private static final long serialVersionUID = 7784677082724913311L;

    private String errorCode;
    private String errorMsg;

    public BusinessException(String errorCode,String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
