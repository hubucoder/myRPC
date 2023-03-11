package org.hubu.framework.core.common.event.listener;

/**
 * 监听器接口
 * @param <T>
 */
public interface IRpcListener<T> {
    void callBack(Object t);
}
