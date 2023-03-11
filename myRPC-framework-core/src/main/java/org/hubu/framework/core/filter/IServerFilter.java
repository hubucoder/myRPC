package org.hubu.framework.core.filter;

import org.hubu.framework.core.common.RpcInvocation;

public interface IServerFilter extends IFilter{

    void doFilter(RpcInvocation rpcInvocation);
}
