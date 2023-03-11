package org.hubu.framework.core.common;

import java.util.concurrent.atomic.AtomicLong;

import static org.hubu.framework.core.common.cache.CommonClientCache.SERVICE_ROUTER_MAP;

// 用于实现路由的轮询效果
public class ChannelFuturePollingRef {

    private final AtomicLong referenceTimes = new AtomicLong(0);


    public ChannelFutureWrapper getChannelFutureWrapper(ChannelFutureWrapper[] arr){
//        ChannelFutureWrapper[] arr = SERVICE_ROUTER_MAP.get(serviceName);
        long i = referenceTimes.getAndIncrement();
        int index = (int) (i % arr.length);
        return arr[index];
    }
}
