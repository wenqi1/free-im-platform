package com.learn.freeim.controller;

import com.alibaba.fastjson.JSON;
import com.learn.freeim.entity.Message;
import com.learn.freeim.entity.Result;
import com.learn.freeim.websocket.ChannelSupervise;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send")
public class SendController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping
    public Result<String> send(@Validated Message message) {
        Long receiver = message.getReceiver();
        Channel channel = ChannelSupervise.findChannel(receiver);
        if (channel != null) {
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        } else {
            // 把消息存入数据库
        }
        return Result.success();
    }
}
