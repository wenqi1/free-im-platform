package com.learn.freeim.webService;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private final ChannelGroup group;
	private final ConcurrentHashMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();
	
	public TextWebSocketFrameHandler(ChannelGroup group) {
		this.group = group;
	}


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame text) throws Exception {
		String msg = text.text();
		
	}


	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		group.add(ctx.channel());
		super.handlerAdded(ctx);
	}


	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
	}

	
	

}
