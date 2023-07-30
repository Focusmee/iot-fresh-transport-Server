package com.example.iotfreshtransportserver.mqtt;

import com.example.iotfreshtransportserver.domain.entity.LightInfo;
import com.example.iotfreshtransportserver.service.LightInfoService;
import com.example.iotfreshtransportserver.service.impl.LightInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * MQTT工具类操作
 *
 * @author Mr.Qu
 * @since  2020/11/18
 */
@Slf4j
@Component
public class MQTTConnect {

//  @Value("${mqtt.broker}")
  private String HOST = "ws://emqx@192.168.1.101:8083/mqtt";
  private final String clientId = "Client" + (int) (Math.random() * 100000000);
  private MqttClient mqttClient;

  /**
   * 客户端connect连接mqtt服务器
   *
   * @param username 用户名
   * @param password 密码
   * @param mqttCallback 回调函数
   **/
  public void setMqttClient(String username, String password, MqttCallback mqttCallback)
      throws MqttException {
    MqttConnectOptions options = mqttConnectOptions(username, password);
        /*if (mqttCallback == null) {
            mqttClient.setCallback(new Callback());
        } else {
        }*/
    mqttClient.setCallback(mqttCallback);
    mqttClient.connect(options);
  }

  /**
   * 客户端connect连接mqtt服务器
   *
   * @param username 用户名
   * @param password 密码
   **/
  public void setMqttClient(String username, String password)
          throws MqttException {
    MqttConnectOptions options = mqttConnectOptions(username, password);
    mqttClient.connect(options);
  }

  /**
   * 客户端connect设置回调函数
   *
   * @param mqttCallback 回调函数
   **/
  public void setMqttClient(MqttCallback mqttCallback)
          throws MqttException {
    mqttClient.setCallback(mqttCallback);
  }


  /**
   * MQTT连接参数设置
   */
  private MqttConnectOptions mqttConnectOptions(String userName, String passWord)
      throws MqttException {
    mqttClient = new MqttClient(HOST, clientId, new MemoryPersistence());
    MqttConnectOptions options = new MqttConnectOptions();
    options.setUserName(userName);
    options.setPassword(passWord.toCharArray());
    options.setConnectionTimeout(30);///默认：30
    options.setAutomaticReconnect(true);//默认：false
    options.setCleanSession(false);//默认：true MQTT协议3.1.1 使用MQTT5.0使用的是Clean Start 与 Session Expiry Interval
    options.setKeepAliveInterval(60);//默认：60
    return options;
  }

  /**
   * 关闭MQTT连接
   */
  public void close() throws MqttException {
    mqttClient.close();
    mqttClient.disconnect();
  }

  /**
   * 向某个主题发布消息 默认qos：1
   */
  public void pub(String topic, String msg) throws MqttException {
    MqttMessage mqttMessage = new MqttMessage();
    //mqttMessage.setQos(2);
    mqttMessage.setPayload(msg.getBytes());
    MqttTopic mqttTopic = mqttClient.getTopic(topic);
    MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
    token.waitForCompletion();
  }

  /**
   * 向某个主题发布消息
   *
   * @param topic: 发布的主题
   * @param msg: 发布的消息
   * @param qos: 消息质量    Qos：0、1、2
   */
  public void pub(String topic, String msg, int qos) throws MqttException {
    MqttMessage mqttMessage = new MqttMessage();
    mqttMessage.setQos(qos);
    mqttMessage.setPayload(msg.getBytes());
    MqttTopic mqttTopic = mqttClient.getTopic(topic);
    MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
    token.waitForCompletion();
  }

  /**
   * 订阅某一个主题 ，此方法默认的的Qos等级为：1
   *
   * @param topic 主题
   */
  public void sub(String topic) throws MqttException {
    mqttClient.subscribe(topic);
  }

  /**
   * 订阅某一个主题，可携带Qos
   *
   * @param topic 所要订阅的主题
   * @param qos 消息质量：0、1、2
   */
  public void sub(String topic, int qos) throws MqttException {
    mqttClient.subscribe(topic, qos);
  }

  /**
   * 例子
   *
   * @param args arg游戏
   * @throws MqttException mqtt例外
   */
  public static void main(String[] args) throws MqttException {
//    MQTTConnect mqttConnect = new MQTTConnect();
//    String msg = "Mr.Qu" + (int) (Math.random() * 100000000);
//
//    mqttConnect.sub("com/iot/init");
//    mqttConnect.pub("com/iot/init", msg);
  }
}