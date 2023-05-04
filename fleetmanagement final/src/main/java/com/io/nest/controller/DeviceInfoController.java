package com.io.nest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.exception.ClusterNotFoundException;
import com.io.nest.exception.DeviceNotFoundException;
import com.io.nest.model.DeviceDetail;
import com.io.nest.service.DeviceInfoService;
import com.io.nest.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/DeviceInformation")
@CrossOrigin("*")

public class DeviceInfoController {
	
	@Autowired
	private DeviceInfoService deviceInfoSerice;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceInfoController.class);
	
	
	
	@PostMapping(value="/addInfo",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postDetail(@RequestBody DeviceDetail ddetail)  {
        	  
        	return new ResponseEntity<>(deviceInfoSerice.addDetail(ddetail),HttpStatus.CREATED);
        
	}
	
	// getting device details corresponding to device id
	@GetMapping("/{did}")
	public ResponseEntity<?> getDetails(@PathVariable(value = Constants.DID) String dId) throws DeviceNotFoundException {
       
       return new ResponseEntity<>(deviceInfoSerice.getDetail(dId),HttpStatus.OK);

	}
	
	// total number of active devices
	@GetMapping("/active")
	public ResponseEntity<?> getActiveDevices() throws DeviceNotFoundException{

		return new ResponseEntity<>(deviceInfoSerice.getActiveDevices(),HttpStatus.OK);

	}
	
	//total number of inactive devices
	@GetMapping("/inactive")
	public ResponseEntity<?> getInActiveDevices() throws DeviceNotFoundException{

		return new ResponseEntity<>(deviceInfoSerice.getInActiveDevices(),HttpStatus.OK);
	
	}
	
	//total number of inactive devices
	@GetMapping("/issue")
	public ResponseEntity<?> getIssueDevices() throws DeviceNotFoundException {

		return new ResponseEntity<>(deviceInfoSerice.getIssueDevices(),HttpStatus.OK);

	}
	
	//get the list of devices 1,2,3
//	@GetMapping("/devices")
//	public  ResponseEntity<?> getDevices() throws ClusterNotFoundException, DeviceNotFoundException {
//		return new ResponseEntity<>(deviceInfoSerice.getDevices(),HttpStatus.OK);
//	}
	//get detailed list of all devices
	@GetMapping("/detaileddevices")
	public  ResponseEntity<?> getDevicesList() throws ClusterNotFoundException, DeviceNotFoundException {

		return new ResponseEntity<>(deviceInfoSerice.getDevicesList(),HttpStatus.OK);

		
	}
	
	@DeleteMapping("/{did}")
	public String deleteDeviceById(@PathVariable (value = Constants.DID) String deviceid) throws DeviceNotFoundException {

		deviceInfoSerice.deleteDeviceById(deviceid);
		return null;
		
	}
	
//	@PutMapping(value="/mapper/update/{did}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> putMobilePhone(@PathVariable (value = Constants.DID) long deviceid, @RequestBody DeviceDetailDto deviceDetailsDto) throws DeviceNotFoundException{
//			return new ResponseEntity<>(deviceInfoSerice.updateDeviceDetails(deviceDetailsDto,deviceid),HttpStatus.OK);
//		
//	}
//	@Operation(summary = "Delete Bulk Devices")
//	@DeleteMapping("/bulkdelete/{id}")
//	public ResponseEntity<?> bulkDeleteFromDraft(@PathVariable(value = "id") 
//			List<Long> id) throws  DeviceNotFoundException, ClusterNotFoundException {
//
//		deviceInfoSerice.bulkdelettion(id);
//		return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
//
//	}
	
	
	
	

}
