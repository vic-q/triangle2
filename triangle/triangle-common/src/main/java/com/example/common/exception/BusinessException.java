package com.example.common.exception;

/**
 * @author wangqing
 */
public class BusinessException extends RuntimeException {

    private int status;

    public BusinessException(int status, String message) {
        super(message);
        this.status = status;
    }
    
    public int getStatus() {
        return status;
    }
}
