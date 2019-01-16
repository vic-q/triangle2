package com.example.aop;

import com.example.common.Result;
import com.example.common.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wangqing
 */
@ControllerAdvice
public class ApiControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result handleBusinessException(BusinessException ex) {

        logger.error("Project throws businessException, errorCode:{}, ex:", ex.getStatus(), ex);

        return Result.newFailResponse().status(ex.getStatus()).msg(ex.getMessage()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result handleIllegalArgumentException(IllegalArgumentException ex) {

        logger.error("Project throws illegalArgumentException", ex);

        return Result.newFailResponse().status(50000).msg(ex.getMessage()).build();
    }

}