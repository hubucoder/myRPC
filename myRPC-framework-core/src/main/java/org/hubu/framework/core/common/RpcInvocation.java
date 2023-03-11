package org.hubu.framework.core.common;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 里面保存了调用的目标类与方法，返回结果
 * 先是通过代理的方式将调用的信息如目标服务名，方法名等封装进去
 */
@Data
@ToString
public class RpcInvocation {

    private static final long serialVersionUID = -3611379458492006176L;

    private String targetMethod;

    private String targetServiceName;

    private Object[] args;
    // 调用的时候会生成一个 uuid，结果返回时要比对 uuid是否能对上，以防返回结果被篡改
    private String uuid;

    private Object response;

    private Map<String,Object> attachments = new HashMap<>();

    // 新增 记录服务端抛出的异常信息
    private Throwable e;

    private int retry;

}
