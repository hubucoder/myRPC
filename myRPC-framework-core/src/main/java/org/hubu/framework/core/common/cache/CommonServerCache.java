package org.hubu.framework.core.common.cache;


import org.hubu.framework.core.common.ServerServiceSemaphoreWrapper;
import org.hubu.framework.core.common.config.ServerConfig;
import org.hubu.framework.core.dispatcher.ServerChannelDispatcher;
import org.hubu.framework.core.filter.server.ServerAfterFilterChain;
import org.hubu.framework.core.filter.server.ServerBeforeFilterChain;
import org.hubu.framework.core.registry.RegistryService;
import org.hubu.framework.core.registry.URL;
import org.hubu.framework.core.registry.zookeeper.AbstractRegister;
import org.hubu.framework.core.serialize.SerializeFactory;
import org.hubu.framework.core.server.ServiceWrapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CommonServerCache {
    // 将需要注册的服务放到这个 map, key 是 serviceName（接口），value 是具体实现类
    public static final Map<String,Object> PROVIDER_CLASS_MAP = new HashMap<>();

    // 保存所有服务的 url
    public static final Set<URL> PROVIDER_URL_SET = new HashSet<>();

    // 负责注册下线订阅取消订阅的实例对象
    public static AbstractRegister REGISTRY_SERVICE;

    public static SerializeFactory SERVER_SERIALIZE_FACTORY;

    public static ServerConfig SERVER_CONFIG;

    public static ServerBeforeFilterChain SERVER_BEFORE_FILTER_CHAIN;
    public static ServerAfterFilterChain SERVER_AFTER_FILTER_CHAIN;

    // 存放每一个服务包装类，包含 service 实例，服务分组，token等
    public static final Map<String, ServiceWrapper> PROVIDER_SERVICE_WRAPPER_MAP = new ConcurrentHashMap<>();

    public static Boolean IS_STARTED = false;
    public static ServerChannelDispatcher SERVER_CHANNEL_DISPATCHER = new ServerChannelDispatcher();

    // 允许同时连接到同一个方法的连接数 单个方法限流
    public static final Map<String, ServerServiceSemaphoreWrapper> SERVER_SERVICE_SEMAPHORE_MAP = new ConcurrentHashMap<>(64);
}
