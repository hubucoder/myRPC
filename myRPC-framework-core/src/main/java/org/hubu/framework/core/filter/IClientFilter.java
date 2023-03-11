package org.hubu.framework.core.filter;

import org.hubu.framework.core.common.ChannelFutureWrapper;
import org.hubu.framework.core.common.RpcInvocation;

import java.util.List;

public interface IClientFilter extends IFilter{


    void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation);
}
