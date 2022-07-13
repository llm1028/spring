package com.example.springdemo1.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liluming
 * @className: ControllerExceptionAdvice
 * @description: 控制器全局异常类
 * @date 2022/7/13 4:21 下午
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultVo MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVo(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResultVo APIExceptionHandler(ApiException e) {
        // log.error(e.getMessage(), e); 由于还没集成日志框架，暂且放着，写上TODO
        return new ResultVo(e.getCode(), e.getMsg(), e.getData());
    }
}
