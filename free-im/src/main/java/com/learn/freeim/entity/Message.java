package com.learn.freeim.entity;

import javax.validation.constraints.NotEmpty;

public class Message {
    @NotEmpty(message = "发送id不能为空")
    private Long sender;
    @NotEmpty(message = "接收id不能为空")
    private Long receiver;
    @NotEmpty(message = "消息类型不能为空")
    private int type;
    private String content;

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
