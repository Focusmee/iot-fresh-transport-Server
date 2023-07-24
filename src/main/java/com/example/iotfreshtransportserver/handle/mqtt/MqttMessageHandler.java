package com.example.iotfreshtransportserver.handle.mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class MqttMessageHandler implements MqttCallback {
    private String messageContent;
    private Object lock = new Object();

    public String getMessageContent() {
        return messageContent;
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

        // 通知等待的线程
//        synchronized (lock) {
//            lock.notify();
//        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void waitForMessage() throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }
}
