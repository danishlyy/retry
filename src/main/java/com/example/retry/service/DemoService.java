package com.example.retry.service;

import com.example.retry.response.DemoResponseDTO;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:45
 */
public interface DemoService {

    DemoResponseDTO demoTry(String id);
}
