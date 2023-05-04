package com.io.nest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.nest.dto.DeviceDetailDto;

import com.io.nest.exception.ClusterNotFoundException;
import com.io.nest.exception.DeviceNotFoundException;
import com.io.nest.mapper.DeviceDetailMapper;
import com.io.nest.model.DeviceDetail;
import com.io.nest.model.Status;
import com.io.nest.repository.ClusterRepository;
import com.io.nest.repository.DeviceRepository;
import com.io.nest.service.DeviceInfoService;


@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private DeviceDetailMapper devicedetailMapper;
	
	@Override
	public DeviceDetail addDetail(DeviceDetail ddetail) {
		return deviceRepository.save(ddetail);
		
	}

	@Override
	public DeviceDetail getDetail(String dId) throws DeviceNotFoundException{
		if(!deviceRepository.existsById(dId)) {
			throw new DeviceNotFoundException("Device with that Id does not exists");
		}
		return deviceRepository.findById(dId).get();
	}

	@Override
	public int getActiveDevices() throws DeviceNotFoundException{
		List<DeviceDetail> ddetail = deviceRepository.findAll();
		int count = 0;
		for(DeviceDetail d: ddetail) {
			if(d.getStatus()==Status.ACTIVE) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getInActiveDevices() throws DeviceNotFoundException{
		List<DeviceDetail> ddetail = deviceRepository.findAll();
		int count = 0;
		for(DeviceDetail d: ddetail) {
			if(d.getStatus()==Status.INACTIVE) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getIssueDevices() throws DeviceNotFoundException {
		List<DeviceDetail> ddetail = deviceRepository.findAll();
		int count = 0;
		for(DeviceDetail d: ddetail) {
			if(d.getStatus()==Status.ISSUES) {
				count++;
			}
		}
		return count;
	}

	@Override
	public List<String> getDevices() throws DeviceNotFoundException {
		List<DeviceDetail> dd = deviceRepository.findAll();
		List<String> s = new ArrayList<>();
		int i =0;
		for(DeviceDetail d: dd) {
			 s.add(d.getDeviceId());	
		
		}
		return s;
	}

	@Override
	public Object getDevicesList() throws DeviceNotFoundException {
		List<DeviceDetail> dd = deviceRepository.findAll();
//		List<Long> s = new ArrayList<>();
//		int i =0;
//		for(DeviceDetail d: dd) {
//			s.add
//		}
		return dd;
	}

    @Override
    public void deleteDeviceById(String deviceid) throws DeviceNotFoundException {
        // TODO Auto-generated method stub
        
    }

//	@Override
//	public void deleteDeviceById(String deviceid) throws DeviceNotFoundException {
//		if(!deviceRepository.existsById(deviceid)) {
//			throw new DeviceNotFoundException("device with that Id does not exists");
//		}
//		
//		deviceRepository.deleteById(deviceid);
//		
//	}

//	@Override
//	public DeviceDetail updateDeviceDetails(DeviceDetailDto deviceDetailsDto, long dId) throws DeviceNotFoundException {
////		deviceEntity = getDetail(dId);
//		DeviceDetail deviceEntity = deviceRepository.findById(dId)
//				.orElseThrow(() -> new DeviceNotFoundException("No Device with that Id Exists"));
////		if(!(deviceDetailsDto.getDeviceName()== null) ){
////			deviceEntity.setDeviceName(deviceDetailsDto.getDeviceName());
////		}
////		if(!(deviceDetailsDto.getIpAddress()== null) ){
////			deviceEntity.setDeviceName(deviceDetailsDto.getIpAddress());
////		}
////		if(!(deviceDetailsDto.getStatus()== null) ){
////			deviceEntity.setStatus(deviceDetailsDto.getStatus());
////		}
////		if(!(deviceDetailsDto.getLastModified()== null) ){
////			deviceEntity.setLastModified(deviceDetailsDto.getLastModified());
////		}
////		if(!(deviceDetailsDto.getAuthType()== null) ){
////			deviceEntity.setAuthType(deviceDetailsDto.getAuthType());
////		}
//		
//		deviceEntity = devicedetailMapper.updateDeviceEntity(deviceDetailsDto, deviceEntity);
//		return deviceRepository.save(deviceEntity);
//	}

//	@Override
//	public void bulkdelettion(List<Long> id) throws DeviceNotFoundException, ClusterNotFoundException {
//		// TODO Auto-generated method stub
//		for(Long d: id) {
//			DeviceDetail dd = deviceRepository.findById(d).get();
//			deviceRepository.deleteById(d);
//		}
//	}

	
}
