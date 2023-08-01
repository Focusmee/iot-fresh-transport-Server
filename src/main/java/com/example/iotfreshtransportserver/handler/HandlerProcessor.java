package com.example.iotfreshtransportserver.handler;

import com.example.iotfreshtransportserver.enums.TopicTypeEnum;
import com.example.iotfreshtransportserver.handler.anno.TopicTypeHandlerAnno;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式，处理type与实现类的映射关系
 */
//@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {
 
    /**
     * 扫描@TopicTypeHandlerAnno，初始化HandlerContext，将其注册到spring容器
     *
     * @param beanFactory bean工厂
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, TopicTypeHandler> handlerMap = new HashMap<>();
        for (TopicTypeEnum temp : TopicTypeEnum.values()) {
            TopicTypeHandler beanInstacle = getBeansWithAnnotation(beanFactory, TopicTypeHandler.class, TopicTypeHandlerAnno.class, temp.getCode());
            handlerMap.put(temp.getCode(), beanInstacle);
        }
        HandlerContext context = new HandlerContext(handlerMap);
        //单例注入
        beanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }
 
    /*
     * 通过父类+注解找到实体类
     */
    private <T> T getBeansWithAnnotation(ConfigurableListableBeanFactory beanFactory, Class<T> manager, Class<? extends TopicTypeHandlerAnno> annotation, String code) throws BeansException {
        if (ObjectUtils.isEmpty(code)) {
            throw new RuntimeException("code is null ");
        }
        Collection<T> tCollection = beanFactory.getBeansOfType(manager).values();
        for (T t : tCollection) {
            TopicTypeHandlerAnno topicTypeHandlerAnno = t.getClass().getAnnotation(annotation);
            if (ObjectUtils.isEmpty(topicTypeHandlerAnno)) {
                throw new RuntimeException("该注解没有写入值 :" + code);
            }
            //注解值是否与code相等
            if (code.equals(topicTypeHandlerAnno.value().getCode())) {
                return t;
            }
        }
        throw new RuntimeException("通过code没有找到该注解对应的实体类 :" + code);
    }
 
}