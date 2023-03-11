package org.hubu.framework.core.common.config;

import lombok.Data;

@Data
public class ClientConfig {

    private String applicationName;

    private String registerAddr;

    private String registerType;

    private String proxyType;

    private String routerStrategy;

    private String clientSerialize;

    /**
     * 客户端发数据的超时时间
     */
    private Integer timeOut;

    /**
     * 客户端最大响应数据体积
     */
    private Integer maxServerRespDataSize;

}
