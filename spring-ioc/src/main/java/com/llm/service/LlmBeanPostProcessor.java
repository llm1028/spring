package com.llm.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liluming
 * @className: LlmBeanPostProcessor
 * @description:
 * @date 2021/12/10 4:31 下午
 */
@Component
public class LlmBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("userService")) {
            // 这里通过jdk动态代理实现，必须有接口
            Object proxyInstance = Proxy.newProxyInstance(LlmBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                // 可以执行切面逻辑
                System.out.println("切面逻辑");

                return method.invoke(bean, args);
            });
            return proxyInstance;
        }
        return bean;
    }
}
