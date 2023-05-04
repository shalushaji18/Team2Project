package com.io.nest.service.impl;



import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.nest.model.TelemetryDataPoint;
import com.io.nest.repository.TelemetryRepository;
import com.io.nest.service.impl.SimulatedDevice.EventCallback;

//Copyright (c) Microsoft. All rights reserved.
//Licensed under the MIT license. See LICENSE file in the project root for full license information.
//This application uses the Azure IoT Hub device SDK for Java
//For samples see: https://github.com/Azure/azure-iot-sdk-java/tree/master/device/iot-device-samples
import com.microsoft.azure.iothub.DeviceClient;
import com.microsoft.azure.iothub.IotHubClientProtocol;
import com.microsoft.azure.iothub.Message;

import lombok.Data;

@Service
@Data
public class SimulatedDeviceTrial {

    // The device connection string to authenticate the device with your IoT hub.
    private static String connString = "HostName=iot-team2nest-training-team2.azure-devices.net;DeviceId=BP-Monitor-001;SharedAccessKey=3jevprK1vBkrzvi4yp+toY91rbF24k7Kuqj2B51Hgjg=;GatewayHostName=vm-team2nest-training-gw0001-team2";
    private static IotHubClientProtocol protocol = IotHubClientProtocol.AMQPS;
    private static DeviceClient client;
    private static String deviceId = "BP-Monitor-001";
    // private UUID pid = UUID.fromString("c01c1081-8dcf-42f8-b7e2-e04ac483b923");
    






    public String iotdataGeneration()
            throws IOException, URISyntaxException{

        client = new DeviceClient(connString, protocol);
        client.open();

        MessageSender sender = new MessageSender();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(sender);

        System.out.println("Press ENTER to exit.");
        System.in.read();
        executor.shutdownNow();
        client.close();
        return null;
    }

    private static class MessageSender implements Runnable  {
        public volatile boolean stopThread = false;
        public void run()  {

        try {
            double avgPressure = 10; // m/s
            Random rand = new Random();

            while (true) {

                double currentSysPressure = avgPressure + rand.nextDouble() * 4 - 2;
                double currentDiaPressure = avgPressure + rand.nextDouble();
                
                TelemetryDataPoint telemetryDataPoint = new TelemetryDataPoint();
     
              
                telemetryDataPoint.diaPressure= currentDiaPressure;
                telemetryDataPoint.sysPressure = currentSysPressure;
                
//                telemetryDataPoint.setDeviceId(deviceId);
//                telemetryDataPoint.setSysPressure(currentSysPressure);
//                TelemetryDataPoint telemetry = TelemetryRepository.findById(deviceId).get();
               

                String msgStr = telemetryDataPoint.serialize();
                Message msg = new Message(msgStr);
                System.out.println("Sending: " + msgStr);
                
                Object lockobj = new Object();
                EventCallback callback = new EventCallback();
                client.sendEventAsync(msg, callback, lockobj);
                
                synchronized (lockobj) {
                  lockobj.wait();
                }
                Thread.sleep(1000);
              }
            } catch (InterruptedException e) {
              System.out.println("Finished.");
            }
     
    }
    }
}


//@Service
//private static class MessageSender implements Runnable {
//
//  private UUID pid = UUID.fromString("c01c1081-8dcf-42f8-b7e2-e04ac483b923");
//  @Autowired
//  private PatientRepository patientRepository;
//
//  public void run() {
//      
//      // System.out.println(patientid);
//      // MessageCallback callback = new MessageCallback();
//      try {
//          // Initialize the simulated telemetry.
//          // double minTemperature = 20;
//          int minHB = 60;
//          int maxHB = 85;
//          int maxSP = 140;
//          int minSP = 100;
//          int maxDP = 100;
//          int minDP = 60;
//          // double minHumidity = 60;
//          Random rand = new Random();
//
//          while (true) {
//              // Simulate telemetry.
//              // double currentTemperature = minTemperature + rand.nextDouble() * 15;
//              // double currentHumidity = minHumidity + rand.nextDouble() * 20;
//              double currentHB = minHB + rand.nextDouble() * (maxHB - minHB + 1);
//              double currentSP = minSP + rand.nextDouble() * (maxSP - minSP + 1);
//              double currentDP = minDP + rand.nextDouble() * (maxDP - minDP + 1);
//              TelemetryDataPoint telemetryDataPoint = new TelemetryDataPoint();
//              // telemetryDataPoint.temperature = currentTemperature;
//              // telemetryDataPoint.humidity = currentHumidity;
//              telemetryDataPoint.heartbeat = (int) currentHB;
//              telemetryDataPoint.systolePressure = (int) currentSP;
//              telemetryDataPoint.diastolePressure = (int) currentDP;
//
//              //SimulatedDeviceIOT sd = new SimulatedDeviceIOT();
//
//              int HB = (int) currentHB;
//              int DP = (int) currentDP;
//              int SP = (int) currentSP;
//              LocalDateTime now = LocalDateTime.now();
//              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//          
//              int id=0;
//              PatientHistory patientHistory = new PatientHistory(id, HB, DP, SP,dtf.format(now));
//              Patient patient = patientRepository.findById(pid).get();
//              patient.getPatientHistory().add(patientHistory);
//              patientRepository.save(patient);
//                System.out.println(patient);
//                patient.getPatientHistory().get(patient.getPatientHistory().size() - 1);
//              
//              // Add the telemetry to the message body as JSON.
//              String msgStr = telemetryDataPoint.serialize();
//              Message msg = new Message(msgStr);
//              //System.out.println(sd.getPid());
//
//              // api
//
//              // api
//
//              // Add a custom application property to the message.
//              // An IoT hub can filter on these properties without access to the message body.
//              msg.setProperty("heartBeatAlert", (currentHB > 82 || currentHB < 64) ? "true" : "false");
//              msg.setProperty("bloodPressureAlert",
//                      (currentSP > 135 || currentSP < 105 || currentDP > 95 || currentDP < 65) ? "true"
//                              : "false");
//              msg.setProperty("heartbeat", Integer.toString(telemetryDataPoint.heartbeat));
//              msg.setProperty("systolepressure", Integer.toString(telemetryDataPoint.systolePressure));
//              msg.setProperty("diastolepressure", Integer.toString(telemetryDataPoint.diastolePressure));
//

//              System.out.println("Sending message: " + msgStr);
//              /*
//               * ssetting message
//               */
//              // appmessage = msgStr;
//              // System.out.println("after message: " + appmessage );
//              Iot iot = new Iot();
//              iot.myString = msgStr;
//              // iot.printString();
//
//              Object lockobj = new Object();
//
//              // Send the message.
//              EventCallback callback = new EventCallback();
//              client.sendEventAsync(msg, callback, lockobj);
//
//              synchronized (lockobj) {
//                  lockobj.wait();
//              }
//              Thread.sleep(10000);
//
//          }
//      } catch (InterruptedException e) {
//          System.out.println("Finished.");
//      }
//  }
//}

