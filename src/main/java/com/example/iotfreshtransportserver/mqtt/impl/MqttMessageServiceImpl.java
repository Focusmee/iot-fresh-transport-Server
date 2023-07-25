package com.example.iotfreshtransportserver.mqtt.impl;

import com.alibaba.fastjson.JSON;
import com.example.iotfreshtransportserver.mqtt.InitCallback;
import com.example.iotfreshtransportserver.mqtt.MQTTConnect;
import com.example.iotfreshtransportserver.mqtt.MqttMessageService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mqttMessage")
public class MqttMessageServiceImpl implements MqttMessageService {
    private final MQTTConnect mqttConnect;
    InitCallback initCallback;

    @Autowired
    public MqttMessageServiceImpl(MQTTConnect mqttConnect, InitCallback initCallback) {
        this.mqttConnect = mqttConnect;
        this.initCallback = initCallback;
    }

    /**
     * 发布
     *
     * @param topic   主题
     * @param message 消息
     */
    public void publish(String topic, String message) {
        try {
            mqttConnect.pub(topic, message);
        } catch (MqttException e) {
            System.out.println("发送失败");
            throw new RuntimeException(e);
        }
    }

    /**
     * 发布
     *
     * @param topic  主题
     * @param entity 实体
     */
    public <T> void publish(String topic, T entity) {
        String message = JSON.toJSONString(entity);
        try {
            mqttConnect.pub(topic, message,1);
        } catch (MqttException e) {
            System.out.println("发送失败");
            throw new RuntimeException(e);
        }
    }

}
