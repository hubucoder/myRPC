package org.hubu.framework.core.filter.server;

import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.filter.IServerFilter;

import java.util.ArrayList;
import java.util.List;

public class ServerBeforeFilterChain {

    private static final List<IServerFilter> iServerFilters = new ArrayList<>();

    public void addServerFilter(IServerFilter iServerFilter) {
        iServerFilters.add(iServerFilter);
    }

    public void doFilter(RpcInvocation rpcInvocation) {
        for (IServerFilter iServerFilter : iServerFilters) {
            iServerFilter.doFilter(rpcInvocation);
        }
    }
}
