package com.io.nest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.model.Cluster;
import com.io.nest.model.DeviceDetail;
import com.io.nest.model.Status;
import com.io.nest.model.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceDetailFleetTest {
	@LocalServerPort
    private int port;

    private String baseUrl="http://localhost";

    private static RestTemplate restTemplate;
    
    private String baseUrl1;
    
    @Autowired
    private TestH2Repository h2Repository;
    
    @Autowired
    private ClusterH2Repository clusterh2Repository;
    
    @Autowired
    private DeviceDetailH2Repository deviceRepository;
    
    @BeforeAll
    public static void init() {
    	restTemplate=new RestTemplate();
    }
    
    @BeforeEach
    public void setUpDevices() {
    	baseUrl.concat(":").concat(port+"").concat("/DeviceInformation");
    	baseUrl=baseUrl+":"+port+"/DeviceInformation/";
    	System.out.println(baseUrl);
    }
    
//    @Test
//	public void testAddDevices() {
//		
//		DeviceDetail ddetail = new DeviceDetail(1,"device1","191.01.69.19",new Date(2021-12-23),Status.ACTIVE,"Symmetric");
//		DeviceDetail response = restTemplate.postForObject("http://localhost:8099/DeviceInformation/addInfo?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f", ddetail, DeviceDetail.class);
//		assertEquals("device1",response.getDeviceName());
//		assertEquals(1,deviceRepository.findAll().size());
//	}
	
	
	

	
	@Test
    @Sql(statements = "INSERT INTO device_detail  (device_id,device_name, ip_address, last_modified,status,auth_type) VALUES (1,'device1','192:33:22:44', '2022-01-01 05:30:01.986000','ACTIVE','Symmetric')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM device_detail WHERE device_id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
     public void testgetDevices() {
		List<DeviceDetail> d =  restTemplate.getForObject("http://localhost:8099/DeviceInformation//detaileddevices?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f", List.class);
		assertEquals(1, d.size());
        assertEquals(1, deviceRepository.findAll().size());

	
	}
	
	@Test
	public void testDeleteDevice() {
		restTemplate.delete("http://localhost:8099/DeviceInformation//2?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f");
		assertEquals(0,deviceRepository.findAll().size());
		
	}
	
//	@Test
//    @Sql(statements = "INSERT INTO device_detail  (device_id,device_name, ip_address, last_modified,status,auth_type) VALUES (1,'device1','192:33:22:44', '2022-01-01 05:30:01.986000','ACTIVE','Symmetric')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Sql(statements = "DELETE FROM device_detail WHERE device_id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//     public void testgetDevicesById() {
//		DeviceDetail d =  restTemplate.getForObject("http://localhost:8099/DeviceInformation/{id}?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f", DeviceDetail.class, 1);
//		assertAll(
//                () -> assertNotNull(d),
//                () -> assertEquals("device1", d.getDeviceName()),
//                () -> assertEquals("192:33:22:44", d.getIpAddress())
//        );
//
//
//	
//	}
	
	@Test
	public void testgetDevicesByIdDeviceNotFoundException() {
		 Assertions.assertThrows(HttpClientErrorException.class,()->
         restTemplate.getForObject("http://localhost:8099/DeviceInformation/7?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",String.class)  ) ;
	}
	
	
	@Test
    @Sql(statements = "INSERT INTO device_detail  (device_id,device_name, ip_address, last_modified,status,auth_type) VALUES (1,'device6','192:33:22:44', '2022-01-01 05:30:01.986000','ACTIVE','Symmetric')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM device_detail WHERE device_name='device6'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
     public void testgetActiveDevices() {
         int deviceDetail = restTemplate.getForObject("http://localhost:8099/DeviceInformation/active?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",int.class);
         assertEquals(2, deviceDetail);
         assertEquals(2, deviceRepository.findAll().size());
     }
	
	@Test
	public void test_state_is_correct() {
	   DeviceDetailDto underTest  = new DeviceDetailDto("device1","191.01.69.19",new Date(2021-12-23),Status.ACTIVE,"Symmetric");
	   assertEquals("device1", underTest.getDeviceName());
	  
	}
	
	@Test
    @Sql(statements = "INSERT INTO device_detail  (device_id,device_name, ip_address, last_modified,status,auth_type) VALUES (1,'device1','192:33:22:44', '2022-01-01 05:30:01.986000','INACTIVE','Symmetric')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM device_detail WHERE device_name='device1'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
     public void testgetInActiveDevices() {
         int deviceDetail = restTemplate.getForObject("http://localhost:8099/DeviceInformation/inactive?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",int.class);
         assertEquals(1, deviceDetail);
         assertEquals(1, deviceRepository.findAll().size());
   }
   
	@Test
    @Sql(statements = "INSERT INTO device_detail  (device_id,device_name, ip_address, last_modified,status,auth_type) VALUES (2,'device2','192:33:22:44', '2022-01-01 05:30:01.986000','ISSUES','Symmetric')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM device_detail WHERE device_name='device1'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
     public void testgetIssueDevices() {
         int deviceDetail = restTemplate.getForObject("http://localhost:8099/DeviceInformation/issue?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",int.class);
         assertEquals(1, deviceDetail);
         assertEquals(2, deviceRepository.findAll().size());
     }
	
	@Test
	public void testDeD() {
		List<DeviceDetail> dd = new ArrayList<DeviceDetail>();
		assertEquals(1, deviceRepository.findAll().size());
	}
	
	
	

}
