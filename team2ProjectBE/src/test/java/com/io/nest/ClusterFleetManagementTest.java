package com.io.nest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.io.nest.model.Cluster;
import com.io.nest.model.DeviceDetail;
import com.io.nest.model.Status;
import com.io.nest.model.User;
import com.io.nest.utils.Constants;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClusterFleetManagementTest {
	
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
    public void setUpCluster() {
    	baseUrl.concat(":").concat(port+"").concat("/Cluster");
    	baseUrl=baseUrl+":"+port+"/Cluster";
    	System.out.println(baseUrl);
    }

	@Test
	public void testAddCluster() {
		Cluster cluster = new Cluster("C1","cluster1");
		Cluster response = restTemplate.postForObject("http://localhost:8099/Cluster/clusterInfo?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f", cluster, Cluster.class);
		assertEquals("cluster1",response.getClusterName());
		assertEquals(1,clusterh2Repository.findAll().size());
	}

	
	@Test
	@Sql(statements = "INSERT INTO cluster (cluster_id,cluster_name) VALUES ('C2','cluster2')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM cluster WHERE cluster_id='C2'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetC() {
		List<Cluster> cluster = restTemplate.getForObject("http://localhost:8099/Cluster/cluster?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f", List.class);
		assertEquals(2, cluster.size());
        assertEquals(2, clusterh2Repository.findAll().size());
		
		
	}
	
//	 @Test
//     public void testInsertCluster() {
//         DeviceDetail deviceDetail=new DeviceDetail(2,"device2","192:33:22:44",new Date(2021-12-23),Status.ISSUES,"Symmetric");
//         DeviceDetail response= restTemplate.postForObject("http://localhost:8099/Cluster/{cid}?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f", deviceDetail,DeviceDetail.class , "C1");
//         assertEquals("device2",response.getDeviceName());
//         assertEquals(1, deviceRepository.findAll().size());
//       
//     }
	 
	 @Test
     @Sql(statements = "INSERT INTO device_detail (c_id,device_id,device_name, ip_address, last_modified,status,auth_type) VALUES ('C1','32','device1','192:33:22:44', '2022-01-01 05:30:01.986000','ACTIVE','Symmetric')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	 @Sql(statements = "DELETE FROM device_detail WHERE c_id='C1'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
        
     public void testGetDeviceDetailByCId() {
            List<DeviceDetail> deviceDetails = restTemplate.getForObject("http://localhost:8099/Cluster/devicedetail/C1?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",List.class);
            assertEquals(1,deviceDetails.size());
            
    }
	 
	 @Test
     @Sql(statements = "INSERT INTO device_detail (c_id,device_id,device_name, ip_address, last_modified,status,auth_type) VALUES ('C1','3','device2','192:33:22:44', '2022-01-01 05:30:01.986000','ACTIVE','Symmetric')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	 @Sql(statements = "DELETE FROM device_detail WHERE c_id='C1'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
        
     public void testGetDeviceCorrespondingCId() {
            List<DeviceDetail> d = restTemplate.getForObject("http://localhost:8099/Cluster/cluster:devices/C1?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",List.class);
            assertEquals(2,d.size());
            
    }
	 
	 @Test   
     public void testGetDeviceDetailByCidClusterException() {
            Assertions.assertThrows(HttpClientErrorException.class,()->
            restTemplate.getForObject("http://localhost:8099/Cluster/devicedetail/C10?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",String.class)  ) ;
           
            
    }
	 
	 @Test   
     public void testgetDevicesClusterException() {
            Assertions.assertThrows(HttpClientErrorException.class,()->
            restTemplate.getForObject("http://localhost:8099/Cluster/cluster:devices/C7?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",String.class)  ) ;
           
            
    }
	 
	 @Test
	 public void testConstants() {
		 Constants c = new Constants();
		 assertEquals(c.CID, "cid");
	 }

	

}
