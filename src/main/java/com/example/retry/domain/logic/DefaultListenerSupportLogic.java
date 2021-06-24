package com.example.retry.domain.logic;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-23 08:57
 * open和close回调出现在整个重试之前和之后，onError应用于各个RetryCallback调用
 */
public class DefaultListenerSupportLogic implements RetryListener {

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        return false;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback,
            Throwable throwable) {

    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
            Throwable throwable) {

    }
}
