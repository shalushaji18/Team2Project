//package com.io.nest.controller;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//import com.io.nest.model.AuthType;
//import com.io.nest.model.DeviceDetail;
//import com.io.nest.model.DeviceStatus;
//import com.io.nest.model.Status;
//import com.io.nest.service.TelemetryDataService;
//import com.microsoft.azure.iot.service.sdk.Device;
//
//@RestController
//public class MqttController {
//    
//   
//    
////    @Autowired
////    MqttGateway mqtGateway;
//    
//    @Autowired
//    TelemetryDataService telemetryservice;
//    
//    @Autowired
//    DeviceUtil deviceUtil;
//    
//    @PostMapping("/sendMessage")
//    public ResponseEntity<?> publish(@RequestBody String mqttMessage) throws Exception{
//       
//      
//       
//     
//        
//        try {
//            System.out.println("message : " + mqttMessage);
//        DeviceDetail dd = new DeviceDetail();
//      
//        
//        JSONObject jsonObject = new JSONObject(mqttMessage);
//        JSONObject getSth = jsonObject.getJSONObject("message"); //to get the value from json
//        Object level = getSth.get("deviceId");
//        Object connectionstatus =getSth.get("connStatus");
//        Object auth =getSth.get("authType");
//        Object status =getSth.get("status");
//        dd.setDeviceId(level.toString());
//        String ds = connectionstatus.toString();
//        String authtype= auth.toString();
//        String sts= status.toString();
////        System.out.println( DeviceStatus.valueOf(ds));
//        dd.setConnStatus(DeviceStatus.valueOf(ds));
//        dd.setAuthType(AuthType.valueOf(authtype));
//        dd.setStatus(Status.valueOf(sts));
//        System.out.println(level);
//        
//   
////        testObject.addProperty("deviceId", dd.getDeviceId());
//        JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
////        mqtGateway.senToMqtt(convertObject.get("message").toString(), convertObject.get("topic").toString());
//        deviceUtil.regDevice(dd);
//        
////        telemetryservice.iotdataGeneration(deviceId, connectionstring);
//        
//
////        Device device = Device.createFromId(level.toString(), null , null);
////        String connectionstring = "HostName=iot-team2nest-training-team2.azure-devices.net;DeviceId=" + level.toString()+";SharedAccessKey="+device.getPrimaryKey()+";GatewayHostName=vm-team2nest-training-gw0001-team2";
////        System.out.println("pk"+ device.getPrimaryKey());
////        System.out.println("device id:");
////        System.out.println("deviceId" + level.toString());
////        System.out.println("connection:" + connectionstring);
//        
////        telemetryservice.iotdataGeneration( level.toString(), connectionstring);
//      
//        return ResponseEntity.ok("Success");
//        }catch(Exception ex) {
//            ex.printStackTrace();
//            return ResponseEntity.ok("fail");
//        }
//    }
//
//}
