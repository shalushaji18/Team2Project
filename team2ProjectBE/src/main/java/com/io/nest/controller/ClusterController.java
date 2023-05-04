package com.io.nest.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.exception.ClusterNotFoundException;
import com.io.nest.model.Cluster;
import com.io.nest.service.ClusterService;
import com.io.nest.utils.Constants;


@RestController
@RequestMapping("/Cluster")
@CrossOrigin("*")

public class ClusterController {
	@Autowired
	private ClusterService clusterSerice;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
	
	//adding cluster id and cluster name to the table Cluster
	 @PostMapping("/clusterInfo")
	    public ResponseEntity<?> postCluster(@RequestBody Cluster c) {
		 
	        	
	        	return new ResponseEntity<>(clusterSerice.addCluster(c),HttpStatus.CREATED);
	        	
	        }
	       
	
	
	//adding device details corresponding to cluster id to the table DeviceDetail
	@PostMapping(value="/{cid}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> postMobile(@RequestBody DeviceDetailDto ddetail,@PathVariable(value = Constants.CID) String cId) {
	        	return new ResponseEntity<>(clusterSerice.insertCluster(ddetail, cId),HttpStatus.CREATED);
		
	}
	
	//checking the status of devices in cluster, if all devices in a cluster are active =>GREEN, else=> RED
//	@GetMapping("/CheckCluster/{cId}")
//	public ResponseEntity<?> getClusterStatus(@PathVariable(value = "cId")String cid) throws ClusterNotFoundException {
//		return new ResponseEntity<>(clusterSerice.getClusterStatus(cid),HttpStatus.OK);
//	}
	
	//get the list of clusters
	@GetMapping("/cluster")
	public  ResponseEntity<?> getCluster() throws ClusterNotFoundException  {
//		try {
		return new ResponseEntity<>(clusterSerice.getCluster(),HttpStatus.OK);
//		}
//		catch(ClusterNotFoundException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
	}
	
	//get list of devices corresponding to the cluster id
	@GetMapping("cluster:devices/{clusterId}")
	public  ResponseEntity<?> getDevices(@PathVariable(value =Constants.CLUSTERID) String cId) throws ClusterNotFoundException {
//		try {
		return new ResponseEntity<>(clusterSerice.getDevicesCorrespongClusterId(cId),HttpStatus.OK);
//		}
//		catch(ClusterNotFoundException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
		
	} 
	//get status of the cluster ,if all devices in a cluster are active =>GREEN, else=> RED
//	@GetMapping("/CheckClusterStatus")
//	public ResponseEntity<?> getClusterStatus() throws ClusterNotFoundException {
//		return new ResponseEntity<>(clusterSerice.getClusterStatus(),HttpStatus.OK);
//	}
	
	//get the devicedetail corresponding to cluster id(search)
	@GetMapping(value = "/devicedetail/{clid}")
	public  ResponseEntity<?> getDeviceDetail(@PathVariable(value =Constants.CLID) String cId) throws ClusterNotFoundException  {

		return new ResponseEntity<>(clusterSerice.getDeviceDetail(cId),HttpStatus.OK);

		
		
	} 
	
}
