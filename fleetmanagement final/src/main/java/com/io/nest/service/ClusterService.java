package com.io.nest.service;

import java.util.List;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.exception.ClusterNotFoundException;
import com.io.nest.model.Cluster;
import com.io.nest.model.DeviceDetail;

public interface ClusterService {
	
	public Cluster addCluster(Cluster c);

	public DeviceDetail insertCluster(DeviceDetailDto ddetail, String cId);

//	public String getClusterStatus(String cid) throws ClusterNotFoundException;

	public Object getCluster() throws ClusterNotFoundException;

	public Object getDevicesCorrespongClusterId(String cId) throws ClusterNotFoundException;

//	public Object getClusterStatus() throws ClusterNotFoundException;

	public List<DeviceDetail> getDeviceDetail(String cId) throws ClusterNotFoundException;


}
