package com.llm.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;
import com.spring.LlmValue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liluming
 * @className: LlmBeanPostProcessor
 * @description: 给拦截的bean的指定注解的属性设置默认值
 * @date 2021/12/10 4:31 下午
 */
@Component
public class LlmValueBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(LlmValue.class)) {

                field.setAccessible(true);
                try {
                    field.set(bean, field.getAnnotation(LlmValue.class).value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
