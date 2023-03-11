package org.hubu.framework.core.common.event.listener;

import io.netty.channel.ChannelFuture;
import org.hubu.framework.core.client.ConnectionHandler;
import org.hubu.framework.core.common.ChannelFutureWrapper;
import org.hubu.framework.core.common.event.IRpcUpdateEvent;
import org.hubu.framework.core.common.event.data.URLChangeWrapper;
import org.hubu.framework.core.common.utils.CommonUtils;
import org.hubu.framework.core.router.Selector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hubu.framework.core.common.cache.CommonClientCache.CONNECT_MAP;
import static org.hubu.framework.core.common.cache.CommonClientCache.IROUTER;

public class ServiceUpdateListener implements IRpcListener<IRpcUpdateEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUpdateListener.class);

    // 该方法的作用是 更新 CONNECT_MAP (删除已经下线的节点，新增新连接的节点)
    @Override
    public void callBack(Object t) {
        //获取到变更节点的子节点数据信息
        URLChangeWrapper urlChangeWrapper = (URLChangeWrapper) t;
        List<ChannelFutureWrapper> channelFutureWrappers = CONNECT_MAP.get(urlChangeWrapper.getServiceName());
        List<String> matchProviderUrl = urlChangeWrapper.getProviderUrl();
        Set<String> finalUrl = new HashSet<>();
        List<ChannelFutureWrapper> finalChannelFutureWrappers = new ArrayList<>();
        for (ChannelFutureWrapper channelFutureWrapper : channelFutureWrappers) {
            String oldServerAddress = channelFutureWrapper.getHost() + ":" + channelFutureWrapper.getPort();
            //如果老的url没有，说明已经被移除了
            if (!matchProviderUrl.contains(oldServerAddress)) {
                continue;
            } else {
                finalChannelFutureWrappers.add(channelFutureWrapper);
                finalUrl.add(oldServerAddress);
            }
        }
        //此时老的url已经被移除了，开始检查是否有新的url
        List<ChannelFutureWrapper> newChannelFutureWrapper = new ArrayList<>();
        for (String newProviderUrl : matchProviderUrl) {
            if (!finalUrl.contains(newProviderUrl)) {
                // 有新增
                ChannelFutureWrapper channelFutureWrapper = new ChannelFutureWrapper();
                String host = newProviderUrl.split(":")[0];
                Integer port = Integer.valueOf(newProviderUrl.split(":")[1]);
                channelFutureWrapper.setPort(port);
                channelFutureWrapper.setHost(host);
                ChannelFuture channelFuture = null;
                try {
                    channelFuture = ConnectionHandler.createChannelFuture(host,port);
                    channelFutureWrapper.setChannelFuture(channelFuture);
                    newChannelFutureWrapper.add(channelFutureWrapper);
                    finalUrl.add(newProviderUrl);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        finalChannelFutureWrappers.addAll(newChannelFutureWrapper);
        //最终更新服务在这里
        CONNECT_MAP.put(urlChangeWrapper.getServiceName(),finalChannelFutureWrappers);
        Selector selector = new Selector();
        selector.setProviderServiceName(urlChangeWrapper.getServiceName());
        IROUTER.refreshRouterArr(selector);

    }
}
