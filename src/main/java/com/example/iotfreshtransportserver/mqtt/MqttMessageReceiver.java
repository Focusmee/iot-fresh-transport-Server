package com.example.iotfreshtransportserver.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Component
public interface MqttMessageReceiver {
    public void processAndStoreAllData() throws MqttException;
//    public void processAndStoreTemperatureInfoData();
//    public void processAndStoreLightInfoData();
//    public void processAndStoreDeviceControl();
//    public void processAndStoreDeviceStatus();
//    public void processAndStoreTransportCabin();
}
