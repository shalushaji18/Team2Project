package com.io.nest.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.io.nest.FleetmanagementApplication;
import com.microsoft.azure.sdk.iot.device.*;


import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;
import com.microsoft.azure.sdk.iot.device.Message;

@Service
public class ConsumerService implements MqttCallback {
    /** The broker url. */
    private static final String brokerUrl ="tcp://localhost:1883";

    /** The client id. */
    private static final String clientId = "clientId";

    /** The topic. */
    private static final String topic = "IOTdevice";
    
    private static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;
    private static DeviceClient client;
	 /** The broker url. */
 public static void main(String[] args) {
    
//        public void SubRun() {
        System.out.println("Subscriber running");
        new ConsumerService().subscribe(topic);
//        }
    }
 public void subscribe(String topic) {
     //  logger file name and pattern to log
     MemoryPersistence persistence = new MemoryPersistence();

     try
     {

         MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
         MqttConnectOptions connOpts = new MqttConnectOptions();
         connOpts.setCleanSession(true);

         System.out.println("checking");
         System.out.println("Mqtt Connecting to broker: " + brokerUrl);

         sampleClient.connect(connOpts);
         System.out.println("Mqtt Connected");

         sampleClient.setCallback(this);
         sampleClient.subscribe(topic);

         System.out.println("Subscribed");
         System.out.println("Listening");

     } catch (MqttException me) {
         System.out.println(me);
     }
 }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
    	
    

        System.out.println("| Topic:" + topic);
        System.out.println("| Message: " +message.toString());
        System.out.println("-------------------------------------------------");
        
//        String connString = null;
//        client = new DeviceClient(connString, protocol);
//        client.open();
//        
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        executor.execute(message);
//        
        JSONObject jsonObj=new JSONObject(message);
//        System.out.print(jsonObj);
        iothub(jsonObj.get("deviceId").toString(),jsonObj.get("currentSysPressure").toString(),jsonObj.get("currentdiaPressure").toString());

    }
    public void iothub(String deviceId,String currentSysPressure, String currentdiaPressure) throws IllegalArgumentException, URISyntaxException, IOException {
       
        String msgStr ="{\"deviceId\":\"" + deviceId  + ",\"CurrentPressure\":"+ currentSysPressure +",\"DiaPressure\":"+ currentdiaPressure +"}";
//        System.out.println(msgStr);
        String connString = "HostName=iot-team2nest-training-team2.azure-devices.net;DeviceId=temperatureDevice17;SharedAccessKey=/36VnyLSGocaRKxVXeJmSQ==" ;
        DeviceClient client = new DeviceClient(connString, protocol);
        client.open();
        try
        {
           
            Message msg = new Message(msgStr);
            msg.setContentType("application/json");
            msg.setMessageId(java.util.UUID.randomUUID().toString());

            EventCallback eventCallback = new EventCallback();
            client.sendEventAsync(msg, eventCallback, msg);
        } 
        
        catch (Exception e)
        {
            e.printStackTrace(); // Trace the exception
        }
       
    }
    
        
        
    
    public class EventCallback implements IotHubEventCallback {
        public void execute(IotHubStatusCode status, Object context) {
          System.out.println("IoT Hub responded to message with status: " + status.name());

          if (context != null) {
            synchronized (context) {
              context.notify();
            }
          }
        }

     
      }




    @Override
    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub
        
    }
 
//    

}

   
	


