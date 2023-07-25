package com.example.iotfreshtransportserver.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目启动 监听主题
 *
 * @author Mr.Qu
 * @since 2020/11/18 0018
 */
@Slf4j
@Component
public class MQTTListener implements ApplicationListener<ContextRefreshedEvent> {

  @Value("${mqtt.username}")
  private String username;
  @Value("${mqtt.password}")
  private String password;
  private final MQTTConnect server;
  private final InitCallback initCallback;

  @Autowired
  public MQTTListener(MQTTConnect server, InitCallback initCallback) {
    this.server = server;
    this.initCallback = initCallback;
  }

  /**
   * 应用程序事件
   * 在监听器中直接订阅主题
   * @param contextRefreshedEvent 背景更新事件
   */
  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    try {
      server.setMqttClient(username, password, initCallback);
      /*
      * 订阅数据主题
      * */
      server.sub("mqtt/TemperatureInfo");
      server.sub("mqtt/LightInfo");
      server.sub("mqtt/DeviceControl");
      server.sub("mqtt/DeviceStatus");
      server.sub("mqtt/TransportCabin");
      /*
      * 订阅上线事件和下线事件主题
      * */
      server.sub("$SYS/brokers/+/clients/#");
    } catch (MqttException e) {
      log.error(e.getMessage(), e);
    }
  }
}