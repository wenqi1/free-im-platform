package com.learn.freeim.webService;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class FreeImServer {
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group = new NioEventLoopGroup();
	private Channel channel;
	
	public ChannelFuture start(InetSocketAddress address){
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(group)
			.channel(NioServerSocketChannel.class)
			.childHandler(createInitializer(channelGroup));
		ChannelFuture future = bootstrap.bind(address);
		future.syncUninterruptibly();
		channel = future.channel();
		return future;
	}

	private ChannelInitializer<Channel> createInitializer(ChannelGroup channelGroup) {
		return new FreeImServerInitializer(channelGroup);
	}
	
	public void destroy(){
		if(channel != null){
			channel.close();
		}
		channelGroup.close();
		group.shutdownGracefully();
	}
	
	public static void main(String[] args) {
		FreeImServer server = new FreeImServer();
		ChannelFuture future = server.start(new InetSocketAddress(8088));
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				server.destroy();
			}
		});
		future.channel().closeFuture().syncUninterruptibly();
	}
}
