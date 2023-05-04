package com.io.nest.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.service.impl.SimulatedDeviceTrial;
import com.io.nest.utils.Constants;

@RestController

@CrossOrigin("*")

public class TelemetryGeneratorController {
    
    @Autowired
   SimulatedDeviceTrial simulatedDevice;
    
    
    
    @PostMapping(value="/{deviceId}")
    public  ResponseEntity<?> post(@RequestBody TelemetryDataPoint telemetry ,@PathVariable(value = "deviceId") String deviceId) throws IOException, URISyntaxException {
                return new ResponseEntity<>(simulatedDevice.iotdataGeneration(),HttpStatus.CREATED);
        
    }
}
