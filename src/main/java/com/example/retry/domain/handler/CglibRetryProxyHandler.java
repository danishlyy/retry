package com.example.retry.domain.handler;

import com.example.retry.common.util.Result;
import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-28 14:31
 */
public class CglibRetryProxyHandler implements MethodInterceptor {

    // 被代理的方法
    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        int maxRetryTimes = 3;
        int retryTimes = 0;
        boolean needRetry = false;
        Object result = null;
        do {
            result = method.invoke(target,objects);
            if (true){
                needRetry = true;
            }
        }while (needRetry && retryTimes++<maxRetryTimes);
        return null;
    }

    public Object getCglibProxy(Object objectTarget){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);
        Object result = enhancer.create();
        return result;
    }

}
