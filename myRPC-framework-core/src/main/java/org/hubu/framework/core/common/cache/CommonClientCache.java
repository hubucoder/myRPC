package org.hubu.framework.core.common.cache;

import org.hubu.framework.core.common.ChannelFuturePollingRef;
import org.hubu.framework.core.common.ChannelFutureWrapper;
import org.hubu.framework.core.common.RpcInvocation;
import org.hubu.framework.core.common.config.ClientConfig;
import org.hubu.framework.core.filter.client.ClientFilterChain;
import org.hubu.framework.core.registry.RegistryService;
import org.hubu.framework.core.registry.URL;
import org.hubu.framework.core.registry.zookeeper.AbstractRegister;
import org.hubu.framework.core.registry.zookeeper.ZookeeperRegister;
import org.hubu.framework.core.router.IRouter;
import org.hubu.framework.core.serialize.SerializeFactory;
import org.hubu.framework.core.spi.ExtensionLoader;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class CommonClientCache {

    // 客户端异步获取调用任务
    public static BlockingQueue<RpcInvocation> SEND_QUEUE = new ArrayBlockingQueue(100);

    // 服务端返回的结果保存在这个 map 里，key=uuid,value=RpcInvocation
    // todo 一直保存返回结果吗
    public static Map<String, Object> RESP_MAP = new HashMap<>();
    // 客户端配置类实例
    public static ClientConfig CLIENT_CONFIG;

    // 保存客户端订阅的服务名称（serviceName）
    public static List<URL> SUBSCRIBE_SERVICE_LIST = new ArrayList<>();
    // 所有客户端的 url 集合（所以用 ConcurrentHashMap）com.sise.test.service -> <<ip:host,urlString>,<ip:host,urlString>>
    public static Map<String, Map<String, String>> URL_MAP = new ConcurrentHashMap<>();
    // 保存所有与客户端建立连接的服务端的地址 不要和 zookeeper节点保存的地址搞混了，不是一回事
    public static Set<String> SERVER_ADDRESS = new HashSet<>();
    // 每次进行远程调用的时候都是从这里面去选择服务提供者(里面保存了实时更新的在线的服务节点，以 serviceName 为 key)
    public static Map<String, List<ChannelFutureWrapper>> CONNECT_MAP = new ConcurrentHashMap<>();

    // 保存的是 被打乱顺序的 CONNECT_MAP.LIST<ChannelFutureWrapper>
    public static Map<String, ChannelFutureWrapper[]> SERVICE_ROUTER_MAP = new ConcurrentHashMap<>();
    // 实现随机路由的轮询效果的实例对象
    public static ChannelFuturePollingRef CHANNEL_FUTURE_POLLING_REF = new ChannelFuturePollingRef();
    public static IRouter IROUTER;

    public static SerializeFactory CLIENT_SERIALIZE_FACTORY;

    // 存放客户端过滤链单例
    public static ClientFilterChain CLIENT_FILTER_CHAIN;

    public static AbstractRegister ABSTRACT_REGISTER;

    public static ExtensionLoader EXTENSION_LOADER = new ExtensionLoader();
}
