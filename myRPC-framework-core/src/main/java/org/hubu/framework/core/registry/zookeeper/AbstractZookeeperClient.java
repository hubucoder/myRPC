package org.hubu.framework.core.registry.zookeeper;

import lombok.Data;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * 基于 zookeeper 的抽象模板类
 */
@Data
public abstract class AbstractZookeeperClient {

    private String zkAddress;
    private int baseSleepTimes;
    private int maxRetryTimes;

    public AbstractZookeeperClient(String zkAddress) {
        this.zkAddress = zkAddress;
        //默认3000ms
        this.baseSleepTimes = 1000;
        this.maxRetryTimes = 3;
    }

    public AbstractZookeeperClient(String zkAddress, Integer baseSleepTimes, Integer maxRetryTimes) {
        this.zkAddress = zkAddress;
        if (baseSleepTimes == null) {
            this.baseSleepTimes = 1000;
        } else {
            this.baseSleepTimes = baseSleepTimes;
        }
        if (maxRetryTimes == null) {
            this.maxRetryTimes = 3;
        } else {
            this.maxRetryTimes = maxRetryTimes;
        }
    }

    public abstract void updateNodeData(String address, String data);

    public abstract Object getClient();

    /**
     * 拉取节点的数据
     */
    public abstract String getNodeData(String path);

    /**
     * 获取指定目录下的字节点数据
     */
    public abstract List<String> getChildrenData(String path);

    /**
     * 创建持久化类型节点
     */
    public abstract void createPersistentData(String address, String data);

    /**
     * 创建带序号的持久化节点
     */
    public abstract void createPersistentWithSeqData(String address, String data);

    /**
     * 创建临时节点数据类型信息
     */
    public abstract void createTemporaryData(String address, String data);

    /**
     * 创建有序且临时类型节点数据信息
     */
    public abstract void createTemporarySeqData(String address, String data);

    /**
     * 设置某个节点的数值
     */
    public abstract void setTemporaryData(String address, String data);

    /**
     * 断开zk的客户端链接
     */
    public abstract void destroy();

    /**
     * 展示该节点下的所有子节点
     */
    public abstract List<String> listNode(String address);

    /**
     * 删除节点下边的数据
     */
    public abstract boolean deleteNode(String address);

    /**
     * 判断是否存在其他节点
     */
    public abstract boolean existNode(String address);

    /**
     * 监听path路径下某个节点的数据变化
     */
    public abstract void watchNodeData(String path, Watcher watcher);

    /**
     * 监听子节点下的数据变化
     */
    public abstract void watchChildNodeData(String path, Watcher watcher);
}
