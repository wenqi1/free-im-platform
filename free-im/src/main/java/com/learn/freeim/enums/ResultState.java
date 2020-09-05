package com.learn.freeim.enums;

/**
 * 响应结果状态
 */
public enum ResultState {
    SUCCESS(1, "成功"),FAIL(0, "失败");

    private final int code;
    private final String msg;

    ResultState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
