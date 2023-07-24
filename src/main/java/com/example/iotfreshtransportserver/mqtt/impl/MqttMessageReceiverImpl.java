package com.example.iotfreshtransportserver.mqtt.impl;

import com.example.iotfreshtransportserver.domain.entity.*;
import com.example.iotfreshtransportserver.handle.*;
import com.example.iotfreshtransportserver.handle.mqtt.InitCallback;
import com.example.iotfreshtransportserver.mqtt.MQTTConnect;
import com.example.iotfreshtransportserver.mqtt.MqttMessageReceiver;
import com.example.iotfreshtransportserver.service.*;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("MqttMessageReceiver")
public class MqttMessageReceiverImpl implements MqttMessageReceiver {

    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;

    private final MQTTConnect mqttConnect;
//    TemperatureInfoHandler temperatureInfoHandler;
//    LightInfoHandler lightInfoHandler;
//    DeviceControlHandler deviceControlHandler;
//    DeviceStatusHandler deviceStatusHandler;
//    TransportCabinHandler transportCabinHandler;

    InitCallback initCallback;

    private boolean initialized = false;

    @Autowired
    public MqttMessageReceiverImpl(MQTTConnect mqttConnect, InitCallback initCallback) {
        this.mqttConnect = mqttConnect;
        this.initCallback = initCallback;
    }

// 间隔时间，单位：毫秒
    private static final long INTERVAL_MS = 5000; // 5秒


    @Override
    @Scheduled(fixedRate = INTERVAL_MS)
    public void processAndStoreAllData() throws MqttException {
        if (!initialized) {
            mqttConnect.setMqttClient(username, password); // Initialize mqttConnect before first execution
            initialized = true;
            mqttConnect.sub("mqtt/TemperatureInfo");
            mqttConnect.sub("mqtt/LightInfo");
            mqttConnect.sub("mqtt/DeviceControl");
            mqttConnect.sub("mqtt/DeviceStatus");
            mqttConnect.sub("mqtt/TransportCabin");
        }
        System.out.println("Hello");
        mqttConnect.setMqttClient(initCallback);
    }

//    public void processAndStoreTemperatureInfoData(MQTTConnect mqttConnect) {
//        try {
//            mqttConnect.setMqttClient(temperatureInfoHandler);
//
//        } catch (MqttException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public void processAndStoreLightInfoData(MQTTConnect mqttConnect) {
//        try{
//            mqttConnect.setMqttClient(lightInfoHandler);
//
//        } catch (MqttException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public void processAndStoreDeviceControl(MQTTConnect mqttConnect) {
//        try {
//            mqttConnect.setMqttClient(deviceControlHandler);
//
//        }
//        catch (MqttException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public void processAndStoreDeviceStatus(MQTTConnect mqttConnect) {
//        try {
//            mqttConnect.setMqttClient(deviceStatusHandler);
//
//        } catch (MqttException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public void processAndStoreTransportCabin(MQTTConnect mqttConnect) {
//        try {
//            mqttConnect.setMqttClient(transportCabinHandler);
//
//        } catch (MqttException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
