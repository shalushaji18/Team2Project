package com.io.nest.service.impl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.exception.ClusterNotFoundException;
import com.io.nest.exception.DeviceNotFoundException;
import com.io.nest.mapper.DeviceDetailMapper;
import com.io.nest.model.Cluster;
import com.io.nest.model.DeviceDetail;
import com.io.nest.model.Status;
import com.io.nest.repository.ClusterRepository;
import com.io.nest.repository.DeviceRepository;
import com.io.nest.service.ClusterService;



@Service
public class ClusterServiceImpl implements ClusterService{
	
	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private DeviceDetailMapper dmapper;
	
	@Override
	public Cluster addCluster(Cluster c) {
		
		return clusterRepository.save(c);
	}

	@Override
	public DeviceDetail insertCluster(DeviceDetailDto ddetail, String cId) {
		Cluster c = clusterRepository.getClusterById(cId);
		DeviceDetail dd = new DeviceDetail();
			dd =	dmapper.updateDeviceEntity(ddetail, dd);
		c.getDeviceDetail().add(dd);
		deviceRepository.save(dd);
		return dd;
	}

//	@Override
//	public String getClusterStatus(String cid) throws ClusterNotFoundException {
//		Cluster c= clusterRepository.getClusterById(cid);
//		List<DeviceDetail> d = c.getDeviceDetail();
//		
//		int flag = 0;
//		String a = "GREEN";
//		String b = "RED";
//		for(DeviceDetail dd: d) {
//			if(dd.getStatus() !=Status.ACTIVE) {
//				flag = 1;
//				break;
//			}
//		}
//		if(flag==0) {
//			return a;
//		}
//		return b;
//	}

	@Override
	public List<String> getCluster() throws ClusterNotFoundException {
		List<Cluster> cluster = clusterRepository.findAll();
		List<String> s = new ArrayList<>();
		int i =0;
		for(Cluster c: cluster) {
			 s.add(c.getClusterId());
			
		
		}
		return s;
		
		
	}

	@Override
	public Object getDevicesCorrespongClusterId(String cId) throws ClusterNotFoundException {
		
		Cluster c= clusterRepository.getClusterById(cId);
		if(!clusterRepository.existsById(cId)) {
			throw new ClusterNotFoundException("Cluster with that Id does not exists");
		}
		List<DeviceDetail> d = c.getDeviceDetail();
		List<String> l = new ArrayList<>();
		for(DeviceDetail dd: d) {
			
			l.add(dd.getDeviceId());
		}
		return l;
	}

//	@Override
//	public Object getClusterStatus() throws ClusterNotFoundException {
//		// TODO Auto-generated method stub
//		List<Cluster> c =clusterRepository.findAll();
//		List<DeviceDetail> d = deviceRepository.findAll();
//		int flag = 0;
//		String a = "ACTIVE";
//		String b = "INACTIVE";
//		List<Status> s = new ArrayList<>();
//		List<String> st=new ArrayList<>();
//		String v1= "GREEN";
//		String v2 ="RED";
//	
////		List<String> d = new ArrayList<>();
//		for(Cluster cluster: c) {
//			s.clear();
//			
//			
//			for(DeviceDetail dd:cluster.getDeviceDetail()) {
//				
//				s.add(dd.getStatus());
////				if(dd.getStatus() != Status.ACTIVE) {
////					
////					flag = 1;
////					break;
////				}
//				
//			}
//			flag =0;
//			System.out.println(s);
//			System.out.println("...................................................");
//			for(Status status: s) {
//				
//				if(!(status.equals(Status.ACTIVE))) {
//					
//					flag=1;
//					break;
//				}
//			}
//			System.out.println(flag);
//			if(flag ==0) {
//				st.add(v1);
//			}else {
//				st.add(v2);
//			}
//			
//		}
//
//		return st;
//		
//	}

	@Override
	public List<DeviceDetail> getDeviceDetail(String cId) throws ClusterNotFoundException {
//		int size =5;
//		int page =5;
//		Pageable pagable = PageRequest.of(page,size);
		
		Cluster c= clusterRepository.getClusterById(cId);
		if(!clusterRepository.existsById(cId)) {
			throw new ClusterNotFoundException("Cluster with that Id does not exists");
		}
		List<DeviceDetail> d = c.getDeviceDetail();
		return d;
	}
	
}
