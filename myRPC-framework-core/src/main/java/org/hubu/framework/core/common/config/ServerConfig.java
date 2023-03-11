package org.hubu.framework.core.common.config;

import lombok.Data;

@Data
public class ServerConfig {
    // server 端口号
    private Integer port;
    // 注册地址
    private String registerAddr;

    private String registerType;
    // 服务名
    private String applicationName;

    private String serverSerialize;

    /**
     * 服务端业务线程数目
     */
    private Integer serverBizThreadNums;

    /**
     * 服务端接收队列的大小
     */
    private Integer serverQueueSize;

    /**
     * 限制服务端最大所能接受的数据包体积
     */
    private Integer maxServerRequestData;

    /**
     * 服务端最大连接数
     */
    private Integer maxConnections;

}
