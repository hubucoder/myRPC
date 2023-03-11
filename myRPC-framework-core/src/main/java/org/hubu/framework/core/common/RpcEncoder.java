package org.hubu.framework.core.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static org.hubu.framework.core.common.constants.RpcConstants.DEFAULT_DECODE_CHAR;


public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol msg, ByteBuf out) throws Exception {
        out.writeShort(msg.getMagicNumber());
        out.writeInt(msg.getContentLength());
        out.writeBytes(msg.getContent());
        // 为了防止发送的数据量过大，而导致一次发送不过来，所以在协议体尾部加入一个特殊分隔符，通过参数定义每次传输的最大数据包体积
        // 这样可以告知服务端每次读取的数据包上限为配置的字节数长度，并且如果在这个这个特殊分隔符内没有读取到完整的协议内容，则属于异常数据包
        out.writeBytes(DEFAULT_DECODE_CHAR.getBytes());
    }
}
