package com.io.nest;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.io.nest.mapper.DeviceDetailMapper;
import com.io.nest.mapper.DeviceDetailMapperImpl;
import com.microsoft.azure.iothub.DeviceClient;





@SpringBootApplication
public class FleetmanagementApplication {
//
//    static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;
//    static DeviceClient client;
//
//
//  
//    protected void sendMessages(String clientConnectionString, String deviceName ) throws Exception {
//
//
//      client = new DeviceClient(clientConnectionString, protocol);
//      client.open();
//      new MessageSender().send(deviceName);
//      client.close();
//    }

 
	public static void main(String[] args) throws IOException {
	
		
		SpringApplication.run(FleetmanagementApplication.class, args);
		  
//		 AzureIotHubClient t = new AzureIotHubClient();
//	      try{
//	        //t.sendMessages(System.getenv("IOTHUB"),System.getenv("DEVICE") );
//	        t.sendMessages(args[0],args[1] );
//	      }catch(Exception ex){
//	        ex.printStackTrace();
//	      }
//	   }
//		try {
//
//			Runtime rt = Runtime.getRuntime();
//            String executablePath = "D:\\TEAM 2 PROJECT\\DownstreamDevice\\bin\\Debug\\netcoreapp3.1";
//            try {
//                Process proc = rt.exec("cmd /c start cmd.exe /K \"cd " + executablePath);
//            }
//            catch (Exception e) {
//                   e.printStackTrace();
//            }
//
//
//	        
//		
//		}
//            catch (Exception e) {
//                e.printStackTrace();
//         }
		
//		

		  
		
	}



	
	
	@Bean
	public DeviceDetailMapper getDeviceDetailMapper() {
		return new DeviceDetailMapperImpl();
	}
	
	
}
