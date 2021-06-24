package com.example.retry.domain.handler;

import com.example.retry.domain.logic.DemoLogic;
import com.example.retry.response.DemoResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:47
 */
@Component
@Slf4j
public class DemoHandler {

    @Autowired
    private DemoLogic demoLogic;

    public DemoResponseDTO demoRetry(String id) {
        return demoLogic.retryDemo(id);
    }
}
