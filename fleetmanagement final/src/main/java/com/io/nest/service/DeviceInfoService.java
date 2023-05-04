package com.io.nest.service;

import java.util.List;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.exception.ClusterNotFoundException;
import com.io.nest.exception.DeviceNotFoundException;
import com.io.nest.model.DeviceDetail;

import io.swagger.v3.oas.annotations.Operation;


public interface DeviceInfoService {

	public DeviceDetail addDetail(DeviceDetail detail);

	public DeviceDetail getDetail(String dId) throws DeviceNotFoundException;

	public int getActiveDevices()throws DeviceNotFoundException;

	public int getInActiveDevices() throws DeviceNotFoundException;

	public int getIssueDevices() throws DeviceNotFoundException;

	public Object getDevices() throws DeviceNotFoundException;

	public Object getDevicesList() throws DeviceNotFoundException;

	public void deleteDeviceById(String deviceid) throws DeviceNotFoundException;

//	public DeviceDetail updateDeviceDetails(DeviceDetailDto deviceDetailsDto, long did) throws DeviceNotFoundException;

//	public void bulkdelettion(List<Long> id)  throws  DeviceNotFoundException, ClusterNotFoundException ;

	
}
