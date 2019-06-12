package com.example.mqtttest.MQTT;

import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHelper {

    public String mqttHost ="tcp://35.229.37.146:1883";
    public static MqttConnectOptions options;
    public static MqttClient client;
    private String myTopic = "NCKU_TOPIC";

    private final String TAG = MqttHelper.class.getSimpleName() + "Test";

    public MqttHelper(final TextView textView){
        try {
            client = new MqttClient(mqttHost, "NCKU2", new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d(TAG, "connectionLost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.d(TAG, "messageArrived: topic: "+ topic);
                    Log.d(TAG, "messageArrived: message: "+ new String(message.getPayload()));
                    textView.setText( new String(message.getPayload()));
                    //
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(TAG, "deliveryComplete: up date state --- " + token.isComplete());
                }
            });
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public MqttHelper(String host, String topic){
        mqttHost = "tcp://"+host;
        myTopic = topic;

        try {
            client = new MqttClient(mqttHost, "NCKU2", new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d(TAG, "connectionLost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.d(TAG, "messageArrived: topic: "+ topic);
                    Log.d(TAG, "messageArrived: message: "+ new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(TAG, "deliveryComplete: up date state --- " + token.isComplete());
                }
            });
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void startSubscribe(){
        try {
            int[] Qos = {1};
            String[] topic1 = {myTopic};
            client.subscribe(topic1, Qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void startPublisher(String message){
        try {
            MqttTopic topic = client.getTopic(myTopic);
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);
            client.publish(myTopic, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

