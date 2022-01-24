package com.llm.service;

import com.spring.*;

/**
 * @author gome001
 * @className: liluming
 * @description:
 * @date 2021/12/9 5:33 下午
 */
@Component("userService")
@Scope("singleton")
// @Scope("prototype")
public class UserService implements UserInterface, BeanNameAware {


    private OrderService orderService;

    @LlmValue(value = "xxx")
    private String name;

    private String beanName;

    @Override
    public void setBeanName(String var1) {
        this.beanName = var1;
    }

    public void test() {
        System.out.println(name);
        System.out.println(beanName);
    }

    /**
     * 做一些初始化的工作
     * @throws Exception
     */
    // @Override
    // public void afterPropertiesSet() {
    //     System.out.println("初始化");
    // }
}
