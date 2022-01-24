package com.example.springplug.test.order;

import org.springframework.core.annotation.Order;

/**
 * @author liluming
 * @className: A
 * @description:
 * @date 2021/12/17 10:53 上午
 */
@Order(3)
public class A {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
