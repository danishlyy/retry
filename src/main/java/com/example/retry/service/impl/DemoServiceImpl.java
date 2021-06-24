package com.example.retry.service.impl;

import com.example.retry.domain.handler.DemoHandler;
import com.example.retry.response.DemoResponseDTO;
import com.example.retry.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:46
 */
@Component
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoHandler demoHandler;

    @Override
    public DemoResponseDTO demoTry(String id) {
        return demoHandler.demoRetry(id);
    }
}
