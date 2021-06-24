package com.example.retry.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 15:09
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(0,"000000","success"),
    FAIL(1,"999999","fail");

    private Integer code;
    private String value;
    private String desc;
}
