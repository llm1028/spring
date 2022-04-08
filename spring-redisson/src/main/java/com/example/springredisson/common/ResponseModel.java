package com.example.springredisson.common;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liluming
 * @className: ResponseModel
 * @description:
 * @date 2022/3/21 3:17 下午
 */
@Data
public class ResponseModel<T> {
    private final static String Y = "Y";
    private final static String N = "N";

    /**
     * 是否成功.Y,N
     */
    private String isSuccess;

    /**
     * 是否成功.Y,N
     */
    private String status = "200";

    /**
     * 错误代码
     * <br/>0:成功、-1:失败、-9:异常
     */
    private String code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 处理结果业务对象
     */
    private T data;
    private String traceId;
    /**
     * 服务器时间
     */
    private String serverTime;

    /**
     * 构造方法.
     */
    public ResponseModel() {
        this(Y, "", "");
    }

    /**
     * 构造方法.
     *
     * @param success 是否成功
     * @param data    处理结果业务对象
     */
    public ResponseModel(String success, T data) {
        this(success, ErrorCodeEnum.HTTP_SUCCESS.getCode(), data);
    }

    /**
     * 构造方法.
     *
     * @param success 是否成功
     * @param errCode 错误代码
     * @param data    数据
     */
    public ResponseModel(String success, String errCode, T data) {
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd " );
        String dateStr = sdf.format(new Date());
        this.isSuccess = success;
        this.code = errCode;
        this.serverTime = dateStr;
        this.data = data;
    }

    /**
     * 构建成功响应结果.
     *
     * @param resultObj 处理结果业务对象
     * @return 处理成功响应结果对象
     */
    public static <T> ResponseModel<T> buildSuccessResponseModel(T resultObj) {
        return new ResponseModel<T>(Y, resultObj);
    }


    /**
     * 构建失败响应结果.
     *
     * @param errCode 错误代码
     * @param errMsg  错误消息
     * @return 处理失败响应结果对象
     */
    public static <T> ResponseModel<T> buildFailureResponseModel(String errCode, String errMsg) {
        return new ResponseModel<T>(N, errCode, errMsg);
    }

    /**
     * 构建失败响应结果.
     *
     * @return 处理异常响应结果对象
     */
    public static <T> ResponseModel<T> buildExceptionResponseModel() {
        return new ResponseModel<T>(N, ErrorCodeEnum.INTERFACE_EXCEPTION.getCode(), ErrorCodeEnum.INTERFACE_EXCEPTION.getName());
    }

    /**
     * 构造方法.
     *
     * @param success 是否成功
     * @param errCode 错误代码
     * @param errMsg  错误消息
     */
    public ResponseModel(String success, String errCode, String errMsg) {
        this.isSuccess = success;
        if (Y.equals(success)) {
            this.status = ErrorCodeEnum.HTTP_SUCCESS.getCode();
        }
        this.code = (StringUtils.isBlank(errCode) ? (Y.equalsIgnoreCase(success) ? ErrorCodeEnum.INTERFACE_SUCCESS.getCode() : ErrorCodeEnum.INTERFACE_FAIL.getCode()) : errCode);
        this.msg = (StringUtils.isBlank(errMsg) ? (Y.equalsIgnoreCase(success) ? ErrorCodeEnum.INTERFACE_SUCCESS.getName() : ErrorCodeEnum.INTERFACE_FAIL.getName()) : errMsg);
    }
}
