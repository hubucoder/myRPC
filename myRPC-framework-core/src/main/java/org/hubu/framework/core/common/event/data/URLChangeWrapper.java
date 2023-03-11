package org.hubu.framework.core.common.event.data;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * 当某节点发生变更时，该类用于保存该节点的子节点的 url 信息及 该节点的服务名
 */
@Data
@ToString
public class URLChangeWrapper {
    private String serviceName;

    private List<String> providerUrl;

    //记录每个ip下边的url详细信息，包括权重，分组等
    private Map<String,String> nodeDataUrl;

}
