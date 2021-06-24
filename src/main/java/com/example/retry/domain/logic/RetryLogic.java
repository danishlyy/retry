package com.example.retry.domain.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ExponentialRandomBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.backoff.NoBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.CompositeRetryPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.ExpressionRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 19:01
 */
@Component
@Slf4j
public class RetryLogic {

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

    /**
     * CircuitBreakerRetryPolicy
     * 增加了熔断的机制，如果不在熔断状态，则允许重试
     *
     * @throws Throwable
     */
    public void circuitBreakerRetryPolicy() throws Throwable{
        RetryTemplate retryTemplate = new RetryTemplate();
        CircuitBreakerRetryPolicy circuitBreakerRetryPolicy = new CircuitBreakerRetryPolicy();
        retryTemplate.setRetryPolicy(circuitBreakerRetryPolicy);
        NoBackOffPolicy noBackOffPolicy = backOffPolicyLogic.noBackOffPolicy();
        retryTemplate.setBackOffPolicy(noBackOffPolicy);
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {
                log.info("circuitBreakerRetryPolicy doWithRetry");
                return null;
            }
        }, new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                log.info("circuitBreakerRetryPolicy recover");
                return null;
            }
        });
    }

    /**
     * ExpressionRetryPolicy
     * 符合表达式就会重试
     * @throws Throwable
     */
    public void expressionRetryPolicy() throws Throwable{
        RetryTemplate retryTemplate = new RetryTemplate();
        ExpressionRetryPolicy expressionRetryPolicy = new ExpressionRetryPolicy("");
        retryTemplate.setRetryPolicy(expressionRetryPolicy);
        ExponentialRandomBackOffPolicy exponentialRandomBackOffPolicy = backOffPolicyLogic
                .exponentialRandomBackOffPolicy(initialInterval, maxInterval, multiplier, null);
        retryTemplate.setBackOffPolicy(exponentialRandomBackOffPolicy);
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {
                log.info("circuitBreakerRetryPolicy doWithRetry");
                return null;
            }
        }, new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                log.info("circuitBreakerRetryPolicy recover");
                return null;
            }
        });

    }

    public void exceptionClassifierRetryPolicy() throws Throwable{
        RetryTemplate retryTemplate = new RetryTemplate();
        ExceptionClassifierRetryPolicy exceptionClassifierRetryPolicy = new ExceptionClassifierRetryPolicy();
        retryTemplate.setRetryPolicy(exceptionClassifierRetryPolicy);
        ExponentialRandomBackOffPolicy exponentialRandomBackOffPolicy = backOffPolicyLogic
                .exponentialRandomBackOffPolicy(initialInterval, maxInterval, multiplier, null);
        retryTemplate.setBackOffPolicy(exponentialRandomBackOffPolicy);
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {
                log.info("circuitBreakerRetryPolicy doWithRetry");
                return null;
            }
        }, new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                log.info("circuitBreakerRetryPolicy recover");
                return null;
            }
        });
    }

    /**
     * 可以组合多个重试策略
     * @throws Throwable
     */
    public void compositeRetryPolicy() throws Throwable{
        RetryTemplate retryTemplate = new RetryTemplate();
        CompositeRetryPolicy compositeRetryPolicy = new CompositeRetryPolicy();
        retryTemplate.setRetryPolicy(compositeRetryPolicy);
        ExponentialRandomBackOffPolicy exponentialRandomBackOffPolicy = backOffPolicyLogic
                .exponentialRandomBackOffPolicy(initialInterval, maxInterval, multiplier, null);
        retryTemplate.setBackOffPolicy(exponentialRandomBackOffPolicy);
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {
                log.info("circuitBreakerRetryPolicy doWithRetry");
                return null;
            }
        }, new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                log.info("circuitBreakerRetryPolicy recover");
                return null;
            }
        });
    }

    /**
     * TimeoutRetryPolicy
     * 默认在1秒内失败都会重试
     */
    public void timeOutRetry() throws Throwable {
        RetryTemplate retryTemplate = new RetryTemplate();
        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(timout);
        retryTemplate.setRetryPolicy(timeoutRetryPolicy);
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {
                return null;
            }
        }, new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                return null;
            }
        });
    }


    /**
     * SimpleRetryPolicy
     * 默认最多重试3次
     */
    public void simpleRetry() throws Throwable {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(maxAttempts);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        FixedBackOffPolicy fixedBackOffPolicy = backOffPolicyLogic.fixedBackOffPolicy(timout);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {

                log.info("-------------doWithRetry-----------");
                return null;
            }
        }, new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                log.info("-------------recover-----------");
                return null;
            }
        });
    }

    public void alwaysRetryPolicy() throws Throwable {
        RetryTemplate retryTemplate = new RetryTemplate();
        AlwaysRetryPolicy alwaysRetryPolicy = new AlwaysRetryPolicy();
        retryTemplate.setRetryPolicy(alwaysRetryPolicy);
        ExponentialBackOffPolicy exponentialBackOffPolicy = backOffPolicyLogic
                .exponentialBackOffPolicy(initialInterval, maxInterval, multiplier, null);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
            @Override
            public Object doWithRetry(RetryContext context) throws Throwable {
                log.info("alwaysRetryPolicy doWithRetry");
                return null;
            }
        }, new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                log.info("alwaysRetryPolicy recover");
                return null;
            }
        });
    }
}
