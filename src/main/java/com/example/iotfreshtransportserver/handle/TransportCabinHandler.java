package com.example.iotfreshtransportserver.handle;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.iotfreshtransportserver.domain.entity.TransportCabin;
import com.example.iotfreshtransportserver.service.TransportCabinService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransportCabinHandler implements MqttCallback {

    private TransportCabinService transportCabinService;
    private String messageContent;

    @Autowired
    public TransportCabinHandler(TransportCabinService transportCabinService) {
        this.transportCabinService = transportCabinService;
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
        TransportCabin transportCabin = JSON.parseObject(messageContent, TransportCabin.class);
        transportCabinService.save(transportCabin);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
