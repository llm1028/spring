package com.example.springdemo1.common;

import lombok.Getter;

/**
 * @author liluming
 * @className: ResultCode
 * @description:
 * @date 2022/7/13 3:21 下午
 */
@Getter
public enum ResultCode implements StatusCode{
    SUCCESS(1000, "请求成功"),
    FAILED(1001, "请求失败"),
    VALIDATE_ERROR(1002, "参数校验失败"),
    RESPONSE_PACK_ERROR(1003, "response返回包装失败");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
