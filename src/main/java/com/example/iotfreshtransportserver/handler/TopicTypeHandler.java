package com.example.iotfreshtransportserver.handler;
/**
 * 主题类型处理定义
 * 使用抽象类，那么子类就只有一个继承了
 */
public abstract class TopicTypeHandler {
    /**
     * @param messageContent
     * @return {@link String}
     */
    abstract public void handler(String messageContent);
}
