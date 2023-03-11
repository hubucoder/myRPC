package org.hubu.framework.core.common.exception;

import org.hubu.framework.core.common.RpcInvocation;

/**
 * 服务端发送的数据太大导致的异常
 */
public class MaxServiceLimitRequestException extends IRpcException {
    public MaxServiceLimitRequestException(RpcInvocation rpcInvocation) {
        super(rpcInvocation);
    }
}
