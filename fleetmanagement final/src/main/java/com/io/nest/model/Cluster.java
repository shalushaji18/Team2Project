package com.io.nest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cluster {
	@Id
	private String clusterId;
	private String clusterName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cId")
	private List<DeviceDetail> deviceDetail = new ArrayList<DeviceDetail>();
	
	public Cluster(String clusterId,String clusterName) {
		this.clusterId=clusterId;
		this.clusterName = clusterName;
	}
	

}
