package com.learn.freeim.websocket;

import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeImWebSocketHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FreeImWebSocketHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest)
            throws Exception {
        String uri = fullHttpRequest.uri();
        int start = uri.indexOf("userId=");
        int end = uri.indexOf("/ws");
        if (start == -1 || end == -1) {
            super.handlerRemoved(channelHandlerContext);
            return;
        }
        Long userId = Long.parseLong(uri.substring(start, end));
        ChannelSupervise.addChannel(userId, channelHandlerContext.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelSupervise.removeChannel(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ChannelSupervise.removeChannel(ctx.channel());
    }

    private ChannelFuture response(Channel channel, TextWebSocketFrame text, String msg){
        text.retain();
        return channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

}
