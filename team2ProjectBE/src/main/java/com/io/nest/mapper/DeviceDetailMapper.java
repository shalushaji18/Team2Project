package com.io.nest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.io.nest.dto.DeviceDetailDto;
import com.io.nest.model.DeviceDetail;


@Mapper
public interface DeviceDetailMapper {
	DeviceDetailDto convertToDeviceDetailDto(DeviceDetail ddetail);
	
	DeviceDetail updateDeviceEntity(DeviceDetailDto deviceDetailDto, @MappingTarget DeviceDetail ddetail);
	

	


}
