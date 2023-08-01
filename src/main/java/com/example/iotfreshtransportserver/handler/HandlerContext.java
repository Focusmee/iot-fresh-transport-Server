package com.example.iotfreshtransportserver.handler;

import net.sf.jsqlparser.statement.select.Top;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 订单策略模式环境
 * 这个类的注入由HandlerProccessor实现
 */
@Component
public class HandlerContext {
    private Map<String, TopicTypeHandler> handlerMap;
 
    /**
     * 构造传参不能直接使用注解扫入
     */
    public HandlerContext(Map<String, TopicTypeHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }
 
    /**
     * 获得实例
     *
     * @param type
     * @return
     */
    public TopicTypeHandler getInstance(String type) {
        if (type == null) {
            throw new IllegalArgumentException("type参数不能为空");
        }
        TopicTypeHandler clazz = handlerMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("该类型没有在枚举OrderTypeHandlerAnno中定义，请定义：" + type);
        }
        return clazz;
    }
 
}