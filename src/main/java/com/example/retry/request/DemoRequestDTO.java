package com.example.retry.request;


import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:52
 */
@Getter
@Setter
@ToString
public class DemoRequestDTO implements Serializable {

    // 如果不设置序列化ID， JVM会默认自动生成一个，序列化ID应该避免为1L
    private static final long serialVersionUID = 2266358457853724466L;

    private String name;

    private Integer age;

    private String address;
}
