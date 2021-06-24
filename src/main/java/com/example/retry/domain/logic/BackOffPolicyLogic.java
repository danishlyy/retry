package com.example.retry.domain.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ExponentialRandomBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.backoff.NoBackOffPolicy;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.stereotype.Component;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-23 09:05
 */
@Component
@Slf4j
public class BackOffPolicyLogic {

    public NoBackOffPolicy noBackOffPolicy(){
        return new NoBackOffPolicy();
    }

    /**
     * 指数递增延迟执行重试 具有随机性
     * @param initialInterval
     * @param maxInterval
     * @param multiplier
     * @param sleeper
     * @return
     */
    public ExponentialRandomBackOffPolicy exponentialRandomBackOffPolicy(long initialInterval,long maxInterval,
            double multiplier,Sleeper sleeper){
        ExponentialRandomBackOffPolicy exponentialRandomBackOffPolicy = new ExponentialRandomBackOffPolicy();
        exponentialRandomBackOffPolicy.setInitialInterval(initialInterval);
        exponentialRandomBackOffPolicy.setMaxInterval(maxInterval);
        exponentialRandomBackOffPolicy.setMultiplier(multiplier);
        exponentialRandomBackOffPolicy.setSleeper(sleeper);
        return exponentialRandomBackOffPolicy;
    }

    /**
     * 指数递增延迟执行重试，默认初始0.1秒，系数是2，那么下次延迟0.2秒，再下次就是延迟0.4秒，如此类推，最大30秒
     * @param initialInterval
     * @param maxInterval
     * @param multiplier
     * @param sleeper
     * @return
     */
    public ExponentialBackOffPolicy exponentialBackOffPolicy(long initialInterval,long maxInterval,
            double multiplier,Sleeper sleeper){
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(initialInterval);
        exponentialBackOffPolicy.setMaxInterval(maxInterval);
        exponentialBackOffPolicy.setMultiplier(multiplier);
        exponentialBackOffPolicy.setSleeper(sleeper);
        return exponentialBackOffPolicy;
    }

    /**
     * FixedBackOffPolicy
     * 默认固定延迟1秒后执行下一次重试
     * @param timout
     * @return
     */
    public FixedBackOffPolicy fixedBackOffPolicy(long timout){
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(timout);
        return fixedBackOffPolicy;
    }
}
