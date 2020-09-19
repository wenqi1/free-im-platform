package com.learn.freeim.webService;

import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelFuture;

@Component
public class FreeServerStart {

    public FreeServerStart() {
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
