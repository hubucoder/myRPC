package org.hubu.framework.core.common;

import io.netty.channel.ChannelFuture;
import lombok.Data;
import lombok.ToString;

/**
 * 客户端连接到服务端的包装类
 */
@Data
@ToString
public class ChannelFutureWrapper {

    private ChannelFuture channelFuture;

    private String host;

    private Integer port;

    private Integer weight;

    private String group;

    public ChannelFutureWrapper() {
    }

    public ChannelFutureWrapper(String host, Integer port,Integer weight) {
        this.host = host;
        this.port = port;
        this.weight = weight;
    }

}
