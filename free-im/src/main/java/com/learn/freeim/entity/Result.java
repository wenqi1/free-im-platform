package com.learn.freeim.entity;

import com.learn.freeim.enums.ResultState;

/**
 * 接口响应实体类
 */
public class Result<T> {
    // 表示接口请求是否成功，1为成功，0为失败
    private Integer code;
    // 接口请求失败时的错误码
    private String errorCode;
    // 错误描述
    private String msg;
    // 接口请求返回数据
    private T data;

    public Result(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String errorCode, String msg) {
        super();
        this.code = code;
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public Result(Integer code, String errorCode, String msg, T data) {
        this.code = code;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultState.SUCCESS.getCode(), data);
    }

    public static <T> Result<T> fail(String errorCode, String msg) {
        return new Result<T>(ResultState.FAIL.getCode(), errorCode, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
