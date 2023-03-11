package org.hubu.framework.core.router;

import lombok.Data;
import org.hubu.framework.core.common.ChannelFutureWrapper;

@Data
public class Selector {

    /**
     * 服务命名
     * eg: com.sise.test.DataService
     */
    private String providerServiceName;

    /**
     * 经过二次筛选之后的future集合
     */
    private ChannelFutureWrapper[] channelFutureWrappers;
}
