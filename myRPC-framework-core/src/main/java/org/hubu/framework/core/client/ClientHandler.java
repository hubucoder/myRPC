package org.hubu.framework.core.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.common.RpcProtocol;

import static org.hubu.framework.core.common.cache.CommonClientCache.CLIENT_SERIALIZE_FACTORY;
import static org.hubu.framework.core.common.cache.CommonClientCache.RESP_MAP;

/**
 * 处理服务端返回的结果，主要是解析出 invocation 对象（里面保存了调用方信息及返回结果）并保存
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcProtocol rpcProtocol = (RpcProtocol) msg;
        byte[] reqContent = rpcProtocol.getContent();
        RpcInvocation rpcInvocation = CLIENT_SERIALIZE_FACTORY.deserialize(reqContent, RpcInvocation.class);
        if(rpcInvocation.getE() != null) {
            rpcInvocation.getE().printStackTrace();
        }
        //如果是单纯异步模式的话，响应Map集合中不会存在映射值
        Object r = rpcInvocation.getAttachments().get("async");
        if (r != null && Boolean.parseBoolean(String.valueOf(r))) {
            ReferenceCountUtil.release(msg);
            return;
        }
        if(!RESP_MAP.containsKey(rpcInvocation.getUuid())){
            throw new IllegalArgumentException("server response is error!");
        }
        // 将返回的结果以 uuid 为 key 保存到 RESP_MAP 中
        RESP_MAP.put(rpcInvocation.getUuid(),rpcInvocation);
        ReferenceCountUtil.release(msg);
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if(channel.isActive()){
            ctx.close();
        }
    }
}
