package com.io.nest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.io.nest.model.Cluster;

@Repository
public interface ClusterRepository extends JpaRepository<Cluster, String>{
	@Query(value = "Select c from Cluster c where c.clusterId=:cId")
	public Cluster getClusterById(@Param(value = "cId") String cId);

}
