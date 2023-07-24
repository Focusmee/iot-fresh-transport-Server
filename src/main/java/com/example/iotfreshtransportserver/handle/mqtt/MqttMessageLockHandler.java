package com.example.iotfreshtransportserver.handle.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MqttMessageLockHandler implements MqttCallback {
    private String messageContent;
    private Lock lock = new ReentrantLock();
    private Condition messageReceived = lock.newCondition();

    public String getMessageContent() {
        lock.lock();
        try {
            // 等待新消息到达
            while (messageContent == null) {
                messageReceived.await();
            }
            // 返回新消息
            return messageContent;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("connectionLost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("topic: " + topic);
        System.out.println("Qos: " + message.getQos());
        System.out.println("message content: " + new String(message.getPayload()));
        // 通知等待的线程
        lock.lock();
        try {
            messageReceived.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
