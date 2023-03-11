package org.hubu.framework.core.filter.client;

import org.hubu.framework.core.common.ChannelFutureWrapper;
import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.common.utils.CommonUtils;
import org.hubu.framework.core.filter.IClientFilter;

import java.util.Iterator;
import java.util.List;

/*
    服务分组过滤器
 */
public class GroupFilterImpl implements IClientFilter {
    @Override
    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        String group = String.valueOf(rpcInvocation.getAttachments().get("group"));
        Iterator<ChannelFutureWrapper> channelFutureWrapperIterator = src.iterator();
        while (channelFutureWrapperIterator.hasNext()){
            ChannelFutureWrapper channelFutureWrapper = channelFutureWrapperIterator.next();
            if (!channelFutureWrapper.getGroup().equals(group)) {
                channelFutureWrapperIterator.remove();
            }
        }
        if (CommonUtils.isEmptyList(src)) {
            throw new RuntimeException("no provider match for group " + group);
        }
    }
}
