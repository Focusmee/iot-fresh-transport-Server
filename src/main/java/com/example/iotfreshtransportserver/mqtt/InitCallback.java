package com.example.iotfreshtransportserver.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.iotfreshtransportserver.domain.dto.ReceivedDataDto;
import com.example.iotfreshtransportserver.domain.entity.*;
import com.example.iotfreshtransportserver.service.*;
import com.example.iotfreshtransportserver.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class InitCallback implements MqttCallback {
    private DeviceControlService deviceControlService;
    private DeviceStatusService deviceStatusService;
    private LightInfoService lightInfoService;
    private TemperatureInfoService temperatureInfoService;
    private TransportCabinService transportCabinService;
    private String messageContent;
    @Autowired
    public InitCallback(DeviceControlService deviceControlService, DeviceStatusService deviceStatusService, LightInfoService lightInfoService, TemperatureInfoService temperatureInfoService, TransportCabinService transportCabinService) {
        this.deviceControlService = deviceControlService;
        this.deviceStatusService = deviceStatusService;
        this.lightInfoService = lightInfoService;
        this.temperatureInfoService = temperatureInfoService;
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
        messageContent = new String(message.getPayload());
        System.out.println("message content: " + messageContent);
        //在这调用Service方法
        //判断Topic进行
        switch (topic) {
            case "mqtt/TemperatureInfo":
                TemperatureInfo temperatureInfo = JSON.parseObject(messageContent, TemperatureInfo.class);
                temperatureInfoService.save(temperatureInfo);
                break;
            case "mqtt/LightInfo":
                LightInfo lightInfo = JSON.parseObject(messageContent, LightInfo.class);
                lightInfoService.save(lightInfo);
                break;
            case "mqtt/DeviceControl":
                DeviceControl deviceControl = JSON.parseObject(messageContent, DeviceControl.class);
                deviceControlService.save(deviceControl);
                break;
            case "mqtt/DeviceStatus":
                DeviceStatus deviceStatus = JSON.parseObject(messageContent, DeviceStatus.class);
                deviceStatusService.save(deviceStatus);
                break;
            case "mqtt/TransportCabin":
                TransportCabin transportCabin = JSON.parseObject(messageContent, TransportCabin.class);
                transportCabinService.save(transportCabin);
                break;
            case "topic1":
                ReceivedDataDto receivedDataDto = JSON.parseObject(messageContent, ReceivedDataDto.class);
                receivedDataDto.setCabinId(receivedDataDto.getVid());

                TemperatureInfo temperatureInfo1 = BeanCopyUtils.copyBean(receivedDataDto, TemperatureInfo.class);
                LightInfo lightInfo1 = BeanCopyUtils.copyBean(receivedDataDto, LightInfo.class);
                try{
                    if(receivedDataDto.getVstatus()!=0) {
                        DeviceStatus deviceStatus1 = BeanCopyUtils.copyBean(receivedDataDto,DeviceStatus.class);
                        deviceStatusService.save(deviceStatus1);
                    } else {
                        DeviceStatus deviceStatus1 = BeanCopyUtils.copyBean(receivedDataDto, DeviceStatus.class);
                        deviceStatusService.updateNormal(deviceStatus1);
                    }
                    if(receivedDataDto.getPid()!=0) {
                        TransportCabin transportCabin1 = BeanCopyUtils.copyBean(receivedDataDto, TransportCabin.class);
                        transportCabinService.save(transportCabin1);
                    }
                    temperatureInfoService.save(temperatureInfo1);
                    lightInfoService.save(lightInfo1);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    break;
                }
            default:
                // Handle unknown topics, or you can ignore them if not needed.
                log.info("unknown topic:");
                /*
                 * 判断是上线还是掉线
                 * */
                try {
                    JSONObject jsonObject = JSON.parseObject(messageContent);
                    String clientId = String.valueOf(jsonObject.get("clientid"));
                    if (topic.endsWith("/disconnected")) {
                        log.info("客户端已掉线：{}", clientId);
                    } else {
                        log.info("客户端已上线：{}", clientId);
                    }
                } catch (JSONException e) {
                    log.error("JSON Format Parsing Exception : {}", messageContent);
                }
                break;
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
