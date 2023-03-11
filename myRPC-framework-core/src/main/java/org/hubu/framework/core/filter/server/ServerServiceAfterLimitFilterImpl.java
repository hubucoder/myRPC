package org.hubu.framework.core.filter.server;

import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.common.ServerServiceSemaphoreWrapper;
import org.hubu.framework.core.common.annotations.SPI;
import org.hubu.framework.core.filter.IServerFilter;

import static org.hubu.framework.core.common.cache.CommonServerCache.SERVER_SERVICE_SEMAPHORE_MAP;

@SPI("after")
public class ServerServiceAfterLimitFilterImpl implements IServerFilter {
    @Override
    public void doFilter(RpcInvocation rpcInvocation) {
        String serviceName = rpcInvocation.getTargetServiceName();
        ServerServiceSemaphoreWrapper serverServiceSemaphoreWrapper = SERVER_SERVICE_SEMAPHORE_MAP.get(serviceName);
        serverServiceSemaphoreWrapper.getSemaphore().release();
    }
}
