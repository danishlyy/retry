package com.example.retry.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-28 11:22
 */
@Slf4j
public class LogUtil {


    /**
     * URL:http://www.baidu.com,
     * METHOD:createPerson,
     * ARGS:aaaa
     * @param args
     */
    public static void main(String[] args) {
        String format = "\nURL:{},\nMETHOD:{},\nARGS:{}";
        log.info(format,"http://www.baidu.com","createPerson","aaaa");

    }



}
