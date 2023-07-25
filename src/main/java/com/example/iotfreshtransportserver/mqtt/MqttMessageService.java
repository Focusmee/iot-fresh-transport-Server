package com.example.iotfreshtransportserver.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Component
public interface MqttMessageService {
    void publish(String topic, String message);
    <T> void publish(String topic, T entity);
}
