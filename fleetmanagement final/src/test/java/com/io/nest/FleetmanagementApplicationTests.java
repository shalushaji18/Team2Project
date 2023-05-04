package com.io.nest;

import static org.junit.jupiter.api.Assertions.*;

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

import com.io.nest.model.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FleetmanagementApplicationTests {
	
	@LocalServerPort
    private int port;

    private String baseUrl="http://localhost";

    private static RestTemplate restTemplate;
    
 
    
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
    public void setUp() {
    	baseUrl.concat(":").concat(port+"").concat("/login").concat("addUser");
    	baseUrl=baseUrl+":"+port+"/login";
    	System.out.println(baseUrl);
    }

//    @BeforeEach
//    public void setUpCluster() {
//    	baseUrl.concat(":").concat(port+"").concat("/Cluster");
//    	baseUrl=baseUrl+":"+port+"/Cluster";
//    	System.out.println(baseUrl);
//    }
    
//    @BeforeEach
//    public void setUpDevices() {
//    	baseUrl.concat(":").concat(port+"").concat("/DeviceInformation");
//    	baseUrl=baseUrl+":"+port+"/DeviceInformation/"+20;
//    	System.out.println(baseUrl);
//    }
    
	@Test
	void contextLoads() {
	}

	@Test
	public void addUser() {
		User user = new User(3,"Shalu","Shaji","shalu@gmail.com","shalu123");
		User response = restTemplate.postForObject("http://localhost:8099/login/addUser?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f", user, User.class);
		
		assertEquals("Shalu", response.getFirstName());
        assertEquals(2, h2Repository.findAll().size());
	}
	
	@Test
	@Sql(statements = "INSERT INTO user (user_id,email, first_name, last_name,password) VALUES (2,'admin@gmail.com', 'admin', 'a','admin123')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM user WHERE user_id=2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUserById() {
		User u = restTemplate.getForObject("http://localhost:8099/login/2?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",User.class,2);
		assertAll(
                () -> assertNotNull(u),
                () -> assertEquals(2, u.getUserId()),
                () -> assertEquals("admin@gmail.com", u.getEmail()),
                () -> assertEquals("admin", u.getFirstName()),
                () -> assertEquals("a", u.getLastName()),
                () -> assertEquals("admin123", u.getPassword())
        );
	}
	
	@Test
	public void testGetUserByIdUserNotFoundException() {
		  Assertions.assertThrows(HttpClientErrorException.class,()->
          restTemplate.getForObject(baseUrl + "/5?access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",String.class)  ) ;
         

	}
	
//	
//	@Test
//	@Sql(statements = "INSERT INTO user (user_id,email, first_name, last_name,password) VALUES (2,'admin@gmail.com', 'admin', 'a','admin123')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM user WHERE user_id=2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//	public void testGetUserByEmail() {
//		User u = restTemplate.getForObject(baseUrl+"/Email/{email}",User.class,"admin@gmail.com");
//		assertAll(
//                () -> assertNotNull(u),
//                () -> assertEquals(2, u.getUserId()),
//                () -> assertEquals("admin@gmail.com", u.getEmail()),
//                () -> assertEquals("admin", u.getFirstName()),
//                () -> assertEquals("a", u.getLastName()),
//                () -> assertEquals("admin123", u.getPassword())
//        );
//	}
	
	@Test
	@Sql(statements = "INSERT INTO user (user_id,email, first_name, last_name,password) VALUES (2,'admin@gmail.com', 'admin', 'a','admin123')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM user WHERE user_id=2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetSignIn() {
		User u = restTemplate.getForObject("http://localhost:8099/login/signin?email=jaya@gmail.com&password=jaya123&access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",User.class);
		assertAll(
               
                () -> assertEquals(1, u.getUserId()),
                () -> assertEquals("jaya@gmail.com", u.getEmail()),
                () -> assertEquals("Jayashree", u.getFirstName()),
                () -> assertEquals("P", u.getLastName()),
                () -> assertEquals("jaya123", u.getPassword())
        );
//		assertEquals("admin", u.getFirstName());
	}
	
	@Test
	public void testSignInUserNotFoundException() {
		  Assertions.assertThrows(HttpClientErrorException.class,()->
          restTemplate.getForObject("http://localhost:8099/login/signin?email=abc@gmail.com&password=abc&access_token=7272527e-7f4a-4b13-84b8-d1b9c3d5494f",String.class)  ) ;
	}
	
}
