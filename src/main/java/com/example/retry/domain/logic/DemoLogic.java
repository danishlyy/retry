package com.example.retry.domain.logic;

import com.example.retry.common.enums.ResponseEnum;
import com.example.retry.common.exception.BusinessException;
import com.example.retry.response.DemoResponseDTO;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:51
 */
@Component
@Slf4j
public class DemoLogic {



    @Retryable
    private void bb() {
    }

    /**
     *  使用 @Retryable 注解
     *  1、应用场景：
     *  （1）微服务之间的feign接口retry
     *  （2）微服务与第三方服务之间API对接的retry
     *  （3）服务与database之间的retry
     *  2、方法级别的retry
     *  （1）增加注解@Retryable注解
     *  （2）value和include等效，主要针对需要进行重试的异常进行重试，如果exclude为空，则默认所有的异常都需要进行重试
     *  （3）maxAttempts：表示最大的重试次数，默认为3次
     *  （4）exclude:表示哪些异常不需要重试，可以忽略
     *  （5）stateful:表示重试是有状态的，默认stateful是false;如果修改为true，则同一个异常再次抛出（同一重试策略和参数），则不会进行重试
     *  （6）@Backoff：指定重试此操作的退避属性
     *  （6.1）value等价于delay属性，默认是1000毫秒，表示延迟1000毫秒后进行下一次重试
     *  （6.2）delay 表示重试的最小间隔，默认值为0，单位毫秒，
     *  （6.3）maxDelay:表示重试之间的最大等待时间（以毫秒为单位）。如果maxDelay的值小于delay的值，
     *  则会默认使用ExponentialBackOffPolicy#DEFAULT_MAX_INTERVAL的值作为maxDelay（30000L毫秒）的值
     *  （6.4）multiplier:如果为正，则用作乘数以生成下一个退避延迟。
     * @param id
     * @return
     */
    @Retryable(include = {BusinessException.class},maxAttempts = 4,backoff = @Backoff(delay = 300,multiplier = 1.5))
    public DemoResponseDTO retryDemo(String id) {
        log.info("thread name:{},thread id:{},time:{}",
                Thread.currentThread().getName(),Thread.currentThread().getId(),
                LocalDateTime.now().getSecond());
        if (StringUtils.isNotBlank(id)){
            throw new BusinessException(ResponseEnum.FAIL.getValue(), ResponseEnum.FAIL.getDesc());
        }
        return DemoResponseDTO.builder().name("zhangsan").age("20").build();
    }


    @Recover
    public DemoResponseDTO recover(BusinessException e){
        log.info("---recover method-----");

        return null;
    }
}
