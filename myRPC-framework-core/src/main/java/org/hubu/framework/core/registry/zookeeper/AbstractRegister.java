package org.hubu.framework.core.registry.zookeeper;

import org.hubu.framework.core.registry.RegistryService;
import org.hubu.framework.core.registry.URL;

import java.util.List;
import java.util.Map;

import static org.hubu.framework.core.common.cache.CommonClientCache.SUBSCRIBE_SERVICE_LIST;
import static org.hubu.framework.core.common.cache.CommonServerCache.PROVIDER_URL_SET;

public abstract class AbstractRegister implements RegistryService {

    @Override
    public void register(URL url) {
        PROVIDER_URL_SET.add(url);
    }

    @Override
    public void unRegister(URL url) {
        PROVIDER_URL_SET.remove(url);
    }

    @Override
    public void subscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.add(url);
    }

    @Override
    public void doUnSubscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.remove(url);
    }

    /**
     * 留给子类扩展
     */
    public abstract void doAfterSubscribe(URL url);

    /**
     * 留给子类扩展
     */
    public abstract void doBeforeSubscribe(URL url);

    /**
     * 留给子类扩展
     */
    public abstract List<String> getProviderIps(String serviceName);

    /**
     * 获取服务的权重信息
     * @return <ip:port --> urlString>,<ip:port --> urlString>,<ip:port --> urlString>,<ip:port --> urlString>
     */
    public abstract Map<String, String> getServiceWeightMap(String serviceName);
}
