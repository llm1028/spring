package com.example.springredisson.common;

/**
 * @author liluming
 * @className: ErrorCodeEnum
 * @description:
 * @date 2022/3/21 3:22 下午
 */
public enum ErrorCodeEnum {
    INTERFACE_SUCCESS(0, "", ""),
    HTTP_SUCCESS(200, "200", "接口调用成功"),
    INTERFACE_FAIL(-1, "-1", "处理失败,请稍后重试"),
    INTERFACE_EXCEPTION(-9, "-9", "服务器繁忙,请稍后重试"),
    ;
    private Integer id;
    private String code;
    private String name;
    private ErrorCodeEnum(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() { return id; }
    public void setId(Integer code) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
