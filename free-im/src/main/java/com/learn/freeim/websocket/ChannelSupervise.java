package com.learn.freeim.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelSupervise {
    private static final ChannelGroup GLOBAL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentHashMap<Long, ChannelId> CHANNEL_MAP = new ConcurrentHashMap<>();

    public static void addChannel(Long userId, Channel channel) {
        GLOBAL_GROUP.add(channel);
        CHANNEL_MAP.put(userId, channel.id());
    }

    public static void removeChannel(Channel channel) {
        GLOBAL_GROUP.remove(channel);
        for(Map.Entry<Long, ChannelId> entry : CHANNEL_MAP.entrySet()){
            Long userId = entry.getKey();
            ChannelId channelId = entry.getValue();
            if (channelId.equals(channel.id())) {
                CHANNEL_MAP.remove(userId);
                return;
            }
        }
    }

    public static Channel findChannel(Long userId) {
        return GLOBAL_GROUP.find(CHANNEL_MAP.get(userId));
    }

}
