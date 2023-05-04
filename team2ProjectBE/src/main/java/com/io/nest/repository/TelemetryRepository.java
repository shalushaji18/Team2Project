package com.io.nest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.nest.controller.TelemetryDataPoint;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryDataPoint, String>{
    

}
