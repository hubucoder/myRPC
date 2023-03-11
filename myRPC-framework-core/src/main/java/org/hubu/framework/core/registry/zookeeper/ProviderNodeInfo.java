package org.hubu.framework.core.registry.zookeeper;

import lombok.Data;
import lombok.ToString;

/**
 * 生产者（服务端）节点信息
 */
@Data
@ToString
public class ProviderNodeInfo {

    private String applicationName;

    private String serviceName;
    private String address;

    private Integer weight;

    private String registryTime;

    private String group;
}
