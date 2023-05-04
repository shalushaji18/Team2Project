package com.io.nest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.nest.model.Cluster;


public interface ClusterH2Repository extends JpaRepository<Cluster,String>{

}
