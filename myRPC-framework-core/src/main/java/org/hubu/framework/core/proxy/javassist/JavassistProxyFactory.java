package org.hubu.framework.core.proxy.javassist;

import org.hubu.framework.core.client.RpcReferenceWrapper;
import org.hubu.framework.core.proxy.ProxyFactory;

public class JavassistProxyFactory implements ProxyFactory {
    @Override
    public <T> T getProxy(RpcReferenceWrapper rpcReferenceWrapper) throws Throwable {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                rpcReferenceWrapper.getAimClass(), new JavassistInvocationHandler(rpcReferenceWrapper));
    }
}
