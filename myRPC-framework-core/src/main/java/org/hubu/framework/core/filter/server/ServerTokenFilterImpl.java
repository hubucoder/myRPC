package org.hubu.framework.core.filter.server;

import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.common.annotations.SPI;
import org.hubu.framework.core.common.utils.CommonUtils;
import org.hubu.framework.core.filter.IServerFilter;
import org.hubu.framework.core.server.ServiceWrapper;

import static org.hubu.framework.core.common.cache.CommonServerCache.PROVIDER_SERVICE_WRAPPER_MAP;

// token 鉴权 目前只到接口级别
@SPI("before")
public class ServerTokenFilterImpl implements IServerFilter {


    @Override
    public void doFilter(RpcInvocation rpcInvocation) {
        String token = String.valueOf(rpcInvocation.getAttachments().get("serviceToken"));
        ServiceWrapper serviceWrapper = PROVIDER_SERVICE_WRAPPER_MAP.get(rpcInvocation.getTargetServiceName());
        String matchToken = String.valueOf(serviceWrapper.getServiceToken());
        if (CommonUtils.isEmpty(matchToken)) {
            return;
        }
        if (!CommonUtils.isEmpty(token) && token.equals(matchToken)) {
            return;
        }
        throw new RuntimeException("token is " + token + " , verify result is false!");
    }
}
