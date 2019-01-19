package com.example.jwt;

/**
 * @author wangqing 
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message) {
        super(message);
    }

}
