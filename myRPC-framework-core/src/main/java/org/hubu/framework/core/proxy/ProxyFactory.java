package org.hubu.framework.core.proxy;

import org.hubu.framework.core.client.RpcReferenceWrapper;

public interface ProxyFactory {
    <T> T getProxy(RpcReferenceWrapper rpcReferenceWrapper) throws Throwable;
}
