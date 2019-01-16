package com.example.common;

import java.io.Serializable;

/**
 * @author wangqing
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int SUCCESS_CODE = 20000;
    private static final int ERROR_CODE = 50000;

    private boolean success;
    private Integer status;
    private String msg;
    private T data;

    public Result() {
    }

    private Result(Builder<T> builder) {
        this.success = builder.success;
        this.status = builder.status;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    public static <T> Builder<T> newSuccessResponse() {
        return new Builder(true);
    }

    public static <T> Builder<T> newFailResponse() {
        return new Builder(false);
    }

    public boolean isSuccess() {
        return success && status == SUCCESS_CODE;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static final class Builder<T> {
        private boolean success = false;
        private Integer status;
        private String msg;
        private T data;

        private Builder(boolean success) {
            this.success = success;
            if (success) {
                this.status = SUCCESS_CODE;
            } else {
                this.status = ERROR_CODE;
            }
        }

        public Result build() {
            return new Result(this);
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder result(T result) {
            this.data = result;
            return this;
        }
    }
}
