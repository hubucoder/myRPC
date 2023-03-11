package org.hubu.framework.core.common.event.listener;

import org.hubu.framework.core.common.event.IRpcDestroyEvent;
import org.hubu.framework.core.registry.URL;

import static org.hubu.framework.core.common.cache.CommonServerCache.PROVIDER_URL_SET;
import static org.hubu.framework.core.common.cache.CommonServerCache.REGISTRY_SERVICE;

public class ServiceDestroyListener implements IRpcListener<IRpcDestroyEvent> {
    @Override
    public void callBack(Object t) {
        for (URL url : PROVIDER_URL_SET) {
            REGISTRY_SERVICE.unRegister(url);
        }
    }
}
