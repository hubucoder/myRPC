package org.hubu.framework.core.server;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import org.hubu.framework.core.common.RpcProtocol;

@Data
public class ServerChannelReadData {
    private RpcProtocol rpcProtocol;

    private ChannelHandlerContext channelHandlerContext;
}
