package org.hubu.framework.core.filter.server;

import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.common.annotations.SPI;
import org.hubu.framework.core.filter.IServerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SPI("before")
public class ServerLogFilterImpl implements IServerFilter {

    private static final Logger logger = LoggerFactory.getLogger(ServerLogFilterImpl.class);
    @Override
    public void doFilter(RpcInvocation rpcInvocation) {
        logger.info(rpcInvocation.getAttachments().get("c_app_name") + " do invoke -----> " + rpcInvocation.getTargetServiceName() + "#" + rpcInvocation.getTargetMethod());
    }
}
