package org.hubu.framework.core.client;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * rpc 远程调用包装类
 */
@Data
public class RpcReferenceWrapper<T> {

    private Class<T> aimClass;

    private Map<String,Object> attachments = new ConcurrentHashMap<>();


    /**
     * 设置容错策略
     *
     */
    public void setTolerant(String tolerant){
        this.attachments.put("tolerant",tolerant);
    }

    /**
     * 失败重试次数
     */
    public int getRetry(){
        if(attachments.get("retry")==null){
            return 0;
        }else {
            return (int) attachments.get("retry");
        }
    }

    public void setRetry(int retry){
        this.attachments.put("retry",retry);
    }

    public boolean isAsync(){
        return Boolean.parseBoolean(String.valueOf(attachments.get("async")));
    }

    public void setAsync(boolean async){
        this.attachments.put("async",async);
    }

    public String getUrl(){
        return String.valueOf(attachments.get("url"));
    }

    public void setUrl(String url){
        attachments.put("url",url);
    }

    public String getServiceToken(){
        return String.valueOf(attachments.get("serviceToken"));
    }

    public void setServiceToken(String serviceToken){
        attachments.put("serviceToken",serviceToken);
    }

    public String getGroup(){
        return String.valueOf(attachments.get("group"));
    }

    public void setGroup(String group){
        attachments.put("group",group);
    }

    public void setTimeOut(int timeOut) {
        attachments.put("timeOut", timeOut);
    }

    public String getTimeOUt() {
        return String.valueOf(attachments.get("timeOut"));
    }

    public Map<String, Object> getAttatchments() {
        return attachments;
    }

    public void setAttatchments(Map<String, Object> attachments) {
        this.attachments = attachments;
    }
}
