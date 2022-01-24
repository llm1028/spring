package com.example.springplug.req;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liluming
 * @className: User
 * @description:
 * @date 2021/12/16 2:55 下午
 */
@Component
public class Student {

    @Value("xxx")
    private User user;

    public void test() {
        System.out.println(user);
    }
}
