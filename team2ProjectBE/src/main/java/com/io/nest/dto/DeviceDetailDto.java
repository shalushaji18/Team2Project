package com.io.nest.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.io.nest.model.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDetailDto {
	private String deviceName;
	private String ipAddress;
	private Date lastModified;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	private String authType;

}
