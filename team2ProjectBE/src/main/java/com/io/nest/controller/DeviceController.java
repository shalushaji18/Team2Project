package com.io.nest.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.io.nest.model.DeviceDetail;
import com.io.nest.model.DeviceStatus;
import com.io.nest.service.ConsumerService;


@RestController
@CrossOrigin("*")
public class DeviceController {
    @Autowired
    DeviceUtil deviceUtil;
    
    @Autowired
    ConsumerService consumerService;
 
    private final Gson gson = new Gson();
    private final static String TEST_DEVICENAME = "testDeviceNr";
    final static String TEST_DEVICETYPE = "IOT Device";
    
    
    //  Creates x amount of testdevices
    @RequestMapping(value = "/createTestDevices", method = RequestMethod.GET)
    public void createTestDevices(@RequestParam int numberOfDevices) throws Exception {
        for(int nr=0; nr<numberOfDevices; nr++ ){
            JsonObject testObject = new JsonObject();
            testObject.addProperty("deviceId", TEST_DEVICENAME+nr);
            testObject.addProperty("deviceType", TEST_DEVICETYPE);
            deviceUtil.registerDevice(gson.toJson(testObject));
        }
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test(@RequestBody String deviceRequest) throws Exception {
        JsonElement element = gson.fromJson (deviceRequest, JsonElement.class);
        JsonObject deviceRequestJ = element.getAsJsonObject();
        String payId = deviceRequestJ.getAsJsonObject("papayaModel").get("payeeId").toString();
        System.out.println("payeeID: "+payId);

    }
    @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
    public String createDevice(@RequestBody DeviceDetail dd) throws Exception {
      
            JsonObject testObject = new JsonObject();
      
//            JsonObject convertObject = new Gson().fromJson(dd);
//        JsonObject convertObject = new Gson().fromJson(dd, JsonObject.class);
       
            testObject.addProperty("deviceId", dd.getDeviceId());
            testObject.addProperty("connStatus", dd.getConnStatus().toString());
            testObject.addProperty("deviceType", TEST_DEVICETYPE);
            deviceUtil.regDevice(dd);
//            mqtGateway.senToMqtt(testObject.get("message").toString(),testObject.get("topic").toString());
//            System.out.println(testObject.get("topic").toString());
            return "device added successfully";
        
    }
//    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
//    public String createDevice(@RequestBody DeviceDetail dd) throws Exception {
//      
//            JsonObject testObject = new JsonObject();
//      
////            JsonObject convertObject = new Gson().fromJson(dd);
////        JsonObject convertObject = new Gson().fromJson(dd, JsonObject.class);
//       
//            testObject.addProperty("deviceId", dd.getDeviceId());
//            testObject.addProperty("connStatus", dd.getConnStatus().toString());
//            testObject.addProperty("deviceType", TEST_DEVICETYPE);
//            deviceUtil.regDevice(dd);////            mqtGateway.senToMqtt(testObject.get("message").toString(),testObject.get("topic").toString());
////            System.out.println(testObject.get("topic").toString());
//            return "device added successfully";
//        
//    }
    
    @RequestMapping(value = "/generateTemetry", method = RequestMethod.POST)
    public String generateTelemtry(@RequestBody String deviceId,String currentSysPressure,String currentdiaPressure) throws IllegalArgumentException, URISyntaxException, IOException{
       
        consumerService.iothub(deviceId,currentSysPressure, currentdiaPressure);
        return "Data sent to iot hub";
    
           
        
    }

  
}
