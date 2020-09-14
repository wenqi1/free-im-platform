package com.learn.freeim.exception;

import org.slf4j.Logger;

/**
 * 自定义异常
 */
public class CommonException extends RuntimeException {
    private String code;
    private Logger logger;

    public CommonException(String code, Throwable cause, Logger logger) {
        this.code = code;
        this.logger = logger;
    }
    
    public CommonException(String code, Logger logger) {
        this.code = code;
        this.logger = logger;
    }

    public String getCode() {
        return code;
    }

    public Logger getLogger() {
        return logger;
    }
}
