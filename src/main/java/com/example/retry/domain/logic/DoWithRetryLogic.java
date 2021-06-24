package com.example.retry.domain.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-24 09:29
 */
@Component
@Slf4j
public class DoWithRetryLogic implements InitializingBean {

    private RetryTemplate retryTemplate = new RetryTemplate();

    @Value("${retry.timeout:1000}")
    private long timout;
    @Value("${retry.initialInterval:500}")
    private long initialInterval;
    @Value("${retry.maxInterval:5000}")
    private long maxInterval;
    @Value("${retry.multiplier:0.5}")
    private double multiplier;
    @Value("${retry.maxAttempts:5}")
    private int maxAttempts;

    @Autowired
    private BackOffPolicyLogic backOffPolicyLogic;

    @Override
    public void afterPropertiesSet() throws Exception {

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(maxAttempts);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        FixedBackOffPolicy fixedBackOffPolicy = backOffPolicyLogic.fixedBackOffPolicy(timout);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
    }

    public void yourMethod() throws Throwable{
        retryTemplate.execute((RetryCallback<Object, Throwable>) context -> {
            // business logic
            log.info("-------------doWithRetry-----------");
            return null;
        }, context -> {
            log.info("-------------recover-----------");
            return null;
        });
    }
}
