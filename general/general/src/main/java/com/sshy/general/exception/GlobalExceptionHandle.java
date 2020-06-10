package com.sshy.general.exception;

import com.sshy.general.vo.ResultObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResultObject ExceptionHandle(Exception e){
        e.printStackTrace();
        return new ResultObject(e);
    }
}
