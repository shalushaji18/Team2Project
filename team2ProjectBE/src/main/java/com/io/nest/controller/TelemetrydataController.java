//package com.io.nest.controller;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.JsonObject;
//import com.io.nest.dto.DeviceDetailDto;
//import com.io.nest.service.TelemetryDataService;
//import com.io.nest.utils.Constants;
//
//
//@RestController
//@RequestMapping("/telemetry")
//@CrossOrigin("*")
//
//public class TelemetrydataController {
//    
//    @Autowired
//    TelemetryDataService telemetryservice;
//    
//    
////    @PostMapping()
////    public  String post() throws IOException, URISyntaxException {
////        TelemetryDataPoint tel = new TelemetryDataPoint();
////        JsonObject testObject = new JsonObject();
////        testObject.addProperty("deviceId", tel.getDeviceId());
////        testObject.addProperty("syspressure", tel.getSysPressure());
////        testObject.addProperty("syspressure", tel.getDiaPressure());
////       
////        telemetryservice.iotdataGeneration();
////        return "device added successfully";
////        
////    }
//    
////    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
////    public  ResponseEntity<?> postMobile(@RequestBody TelemetryDataPoint tda) throws IOException, URISyntaxException {
////                return new ResponseEntity<>(telemetryservice.iotdataGeneration(),HttpStatus.CREATED);
////        
////    }
//    
//    @PostMapping()
//    public  ResponseEntity<?> post(@RequestParam String deviceId, @RequestParam String connString) throws IOException, URISyntaxException {       
//        return new ResponseEntity<>(telemetryservice.iotdataGeneration(deviceId, connString),HttpStatus.CREATED);
//        
//    }
//    
//
//}
