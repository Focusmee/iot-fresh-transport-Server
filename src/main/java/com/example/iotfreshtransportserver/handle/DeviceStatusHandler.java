package com.example.iotfreshtransportserver.handle;

import com.alibaba.fastjson.JSON;
import com.example.iotfreshtransportserver.domain.entity.DeviceControl;
import com.example.iotfreshtransportserver.domain.entity.DeviceStatus;
import com.example.iotfreshtransportserver.service.DeviceControlService;
import com.example.iotfreshtransportserver.service.DeviceStatusService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.CallbackHandler;

@Slf4j
@Component
public class DeviceStatusHandler implements MqttCallback {

    private DeviceStatusService deviceStatusService;
    private String messageContent;

    @Autowired
    public DeviceStatusHandler(DeviceStatusService deviceStatusService) {
        this.deviceStatusService = deviceStatusService;
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("connectionLost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("topic: " + topic);
        System.out.println("Qos: " + message.getQos());
        messageContent =  new String(message.getPayload());
        System.out.println("message content: " + messageContent);
        //在这调用Service方法
        DeviceStatus deviceStatus = JSON.parseObject(messageContent, DeviceStatus.class);
        deviceStatusService.save(deviceStatus);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
