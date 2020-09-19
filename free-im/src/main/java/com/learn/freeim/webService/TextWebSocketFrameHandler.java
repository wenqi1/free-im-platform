package com.learn.freeim.webService;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);
    
    private final ChannelGroup group;
    private static ConcurrentHashMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame text) throws Exception {
        String msg = text.text();
        if (msg == null || msg.isEmpty()) {
            response(ctx.channel(), text, "消息体为空");
            return;
        }
        LOGGER.info("接收到消息: {}", msg);
        if(!msg.contains("|")){
            response(ctx.channel(), text, "消息格式错误");
            return;
        }
        if (msg.contains("connect")) {
            String[] contents = msg.split("\\|");
            String account = contents[1];
            group.add(ctx.channel());
            channelMap.put(account, ctx.channel().id());
            response(ctx.channel(), text, "连接到服务器");
            return;
        }
        String[] contents = msg.split("\\|");
        String send = contents[0];
        String receiver = contents[1];
        String type = contents[2];
        String content = contents[3];
        ChannelId channelId = channelMap.get(receiver);
        if (channelId == null) {
            return;
        }
        Channel channel = group.find(channelId);
        if (channel == null) {
            return;
        }
        ChannelFuture future = response(channel, text, content);
        //自定义就可以将发送方和接收方消息存库
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    //如果消息发送成功
                } else {
                    //如果消息发送失败
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // ChannelGroup 会自动移除channel channelMap需要手动管理
        Iterator<Entry<String, ChannelId>> iterator = channelMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, ChannelId> entry = iterator.next();
            if (entry.getValue().equals(ctx.channel().id())) {
                iterator.remove();
                break;
            }
        }
        super.handlerRemoved(ctx);
    }

    private ChannelFuture response(Channel channel, TextWebSocketFrame text, String msg){
        text.retain();
        return channel.writeAndFlush(new TextWebSocketFrame(msg));
    }
}
