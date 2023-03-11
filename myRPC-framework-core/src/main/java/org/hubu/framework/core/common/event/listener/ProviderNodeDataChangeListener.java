package org.hubu.framework.core.common.event.listener;

import org.hubu.framework.core.common.ChannelFutureWrapper;
import org.hubu.framework.core.common.event.IRpcNodeChangeEvent;
import org.hubu.framework.core.registry.URL;
import org.hubu.framework.core.registry.zookeeper.ProviderNodeInfo;

import java.util.List;

import static org.hubu.framework.core.common.cache.CommonClientCache.CONNECT_MAP;
import static org.hubu.framework.core.common.cache.CommonClientCache.IROUTER;

// 用于监听 节点权重是否发生了变化
public class ProviderNodeDataChangeListener implements IRpcListener<IRpcNodeChangeEvent> {
    @Override
    public void callBack(Object t) {
        ProviderNodeInfo providerNodeInfo = ((ProviderNodeInfo) t);
        List<ChannelFutureWrapper> channelFutureWrappers =  CONNECT_MAP.get(providerNodeInfo.getServiceName());
        for (ChannelFutureWrapper channelFutureWrapper : channelFutureWrappers) {
            String address = channelFutureWrapper.getHost()+":"+channelFutureWrapper.getPort();
            if(address.equals(providerNodeInfo.getAddress())){
                //修改权重
                channelFutureWrapper.setWeight(providerNodeInfo.getWeight());
                channelFutureWrapper.setGroup(providerNodeInfo.getGroup());
                URL url = new URL();
                url.setServiceName(providerNodeInfo.getServiceName());
                //更新权重 这里对应了文章顶部的 RandomRouterImpl 类
                IROUTER.updateWeight(url);
                break;
            }
        }
    }
}
