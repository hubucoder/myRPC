package org.hubu.framework.core.filter.client;

import org.hubu.framework.core.common.ChannelFutureWrapper;
import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.filter.IClientFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hubu.framework.core.common.cache.CommonClientCache.CLIENT_CONFIG;

public class ClientLogFilterImpl implements IClientFilter {

    private static final Logger logger = LoggerFactory.getLogger(ClientLogFilterImpl.class);

    @Override
    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        rpcInvocation.getAttachments().put("c_app_name",CLIENT_CONFIG.getApplicationName());
        logger.info(rpcInvocation.getAttachments().get("c_app_name")+" do invoke -----> "+rpcInvocation.getTargetServiceName());
    }
}
