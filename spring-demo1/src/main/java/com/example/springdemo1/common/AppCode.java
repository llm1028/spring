package com.example.springdemo1.common;

import lombok.Getter;

/**
 * @author liluming
 * @className: AppCode
 * @description: 异常状态码枚举，既然是状态码，那就肯定要实现我们的标准接口 StatusCode。
 * @date 2022/7/13 5:20 下午
 */
@Getter
public enum  AppCode implements StatusCode {

    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常");

    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
