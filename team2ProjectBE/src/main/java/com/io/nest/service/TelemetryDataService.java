//
//package com.io.nest.service;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.Random;
//import java.util.Scanner;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.io.nest.controller.TelemetryDataPoint;
//import com.io.nest.repository.TelemetryRepository;
//import com.microsoft.azure.iothub.DeviceClient;
//import com.microsoft.azure.iothub.IotHubClientProtocol;
//import com.microsoft.azure.iothub.IotHubEventCallback;
//import com.microsoft.azure.iothub.IotHubStatusCode;
//import com.microsoft.azure.iothub.Message;
//
//import lombok.Data;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//
//
//
//@Service
//
//public class TelemetryDataService {
//    @Autowired
//    TelemetryRepository repo;
////    private static String connString = "" ;
//    private static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;
//    private static DeviceClient client;
////    private static String deviceId = "BP-Monitor-001";
//    public static String value = "";
//    public String iotdataGeneration(String deviceId, String connString) throws IOException, URISyntaxException{
//           value = deviceId;
////        Scanner sc = new Scanner(System.in);
////  
////        String connString = sc.nextLine();
//        // System.out.println("device id :");
//        // String devId = sc.nextLine();
//        
//        // Connect to the IoT hub.
//     
//        client = new DeviceClient(connString, protocol);
//        client.open();
//        TelemetryDataPoint telemetryDataPoint = new TelemetryDataPoint();
//        telemetryDataPoint.deviceId = value;
//        repo.save(telemetryDataPoint);
//        // Create new thread and start sending messages 
//      
//        MessageSender sender = new MessageSender();
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        executor.execute(sender);
//        
//        // Stop the application.
//        System.out.println("Press ENTER to exit.");
//        System.in.read();
//        executor.shutdownNow();
////        client.close();
//        return value;
//        
//      }
//    
////    private static class MessageSender implements Runnable  {
////        public volatile boolean stopThread = false;
////        public void run()  {
////
////        try {
////            double avgPressure = 10; // m/s
////            Random rand = new Random();
////           
////
////                //Topic name
////                String topic        = "IOTdevice";
////                //data to be send
////                String content      = "";
////                int qos             = 0;
////                /*hostname is localhost as mqtt publisher and broker are
////                  running on the same computer*/
////                String broker       = "tcp://localhost:1883";
////                String clientId     = "JavaSample";
////                MemoryPersistence persistence = new MemoryPersistence();
////
////                
////
////        
////                
////
////                double currentSysPressure =  rand.nextInt(90,180);
////                double currentdiaPressure =rand.nextInt(60,100) ;
////                
////             
////                TelemetryDataPoint telemetryDataPoint = new TelemetryDataPoint();
////                
////               
////              
////              
////                System.out.println("deviceid" + value);
////                telemetryDataPoint.deviceId =value;
////                telemetryDataPoint.sysPressure = currentSysPressure;
////                telemetryDataPoint.diaPressure = currentdiaPressure;
////
////                String msgStr = telemetryDataPoint.serialize();
////                Message msg = new Message(msgStr);
////               
////              
////                Object lockobj = new Object();
////                EventCallback callback = new EventCallback();
////                client.sendEventAsync(msg, callback, lockobj);
////                
////                synchronized (lockobj) {
////                  lockobj.wait();
////                }
////                Thread.sleep(2000);
//////                
////              
////                
////        }
////            
////    }
////    
////    }
//    private static class EventCallback implements IotHubEventCallback {
//        public void execute(IotHubStatusCode status, Object context) {
//          System.out.println("IoT Hub responded to message with status: " + status.name());
//
//          if (context != null) {
//            synchronized (context) {
//              context.notify();
//            }
//          }
//        }
//
//     
//      }
//
//}
