package com.io.nest;



import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.google.gson.Gson;
import com.io.nest.mapper.DeviceDetailMapper;
import com.io.nest.mapper.DeviceDetailMapperImpl;
import com.io.nest.service.ConsumerService;
import com.microsoft.azure.sdk.iot.device.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


@SpringBootApplication
public class FleetmanagementApplication {
    @Autowired
   public ConsumerService consumer;

    


	public static void main(String[] args) throws IOException, IllegalArgumentException, URISyntaxException {
	
	  
		SpringApplication.run(FleetmanagementApplication.class, args);
		
		




	}
	
	
	
	@Bean
	public DeviceDetailMapper getDeviceDetailMapper() {
		return new DeviceDetailMapperImpl();
	}
	
	
}
