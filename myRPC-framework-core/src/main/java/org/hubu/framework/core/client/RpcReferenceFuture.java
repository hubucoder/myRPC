package org.hubu.framework.core.client;

import lombok.Data;

@Data
public class RpcReferenceFuture<T>  {

    private RpcReferenceWrapper rpcReferenceWrapper;

    private Object response;
}
