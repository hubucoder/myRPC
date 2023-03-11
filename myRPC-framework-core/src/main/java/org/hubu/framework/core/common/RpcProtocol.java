package org.hubu.framework.core.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import static org.hubu.framework.core.common.constants.RpcConstants.MAGIC_NUMBER;

/**
 * 自定义协议体，在客户端与服务端通信的时候就是传输的这个对象
 */
@Data
@ToString
public class RpcProtocol implements Serializable {
    private static final long serialVersionUID = 5359096060555795690L;

    // 魔数
    private short magicNumber = MAGIC_NUMBER;
    // 内容长度
    private int contentLength;
    // 传输的实际内容，Invocation 对象
    private byte[] content;

    public RpcProtocol(byte[] content) {
        this.contentLength = content.length;
        this.content = content;
    }
}
