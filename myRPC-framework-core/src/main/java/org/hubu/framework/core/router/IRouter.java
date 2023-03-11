package org.hubu.framework.core.router;

import org.hubu.framework.core.common.ChannelFutureWrapper;
import org.hubu.framework.core.registry.URL;

public interface IRouter {

    // selector 就是 服务名 serviceName
    /**
     * 刷新路由数组
     */
    void refreshRouterArr(Selector selector);

    /**
     * 获取到请求到连接通道
     */
    ChannelFutureWrapper select(Selector selector);

    /**
     * 更新权重信息
     */
    void updateWeight(URL url);
}
