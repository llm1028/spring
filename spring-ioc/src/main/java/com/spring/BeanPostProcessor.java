package com.spring;

import com.sun.istack.internal.Nullable;

/**
 * @author liluming
 * @className: BeanPostProcessor
 * @description:
 * @date 2021/12/10 4:30 下午
 */
public interface BeanPostProcessor {

    /**
     * @description: 初始化之前
     * @author liluming
     * @date 2021/12/10 4:31 下午
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    /**
     * @description: 初始化之后
     * @author liluming
     * @date 2021/12/10 4:31 下午
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
