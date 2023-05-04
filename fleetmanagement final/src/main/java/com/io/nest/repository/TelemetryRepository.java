package com.io.nest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.io.nest.model.DeviceDetail;
import com.io.nest.model.TelemetryDataPoint;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryDataPoint, String>{
//    @Query("SELECT d  FROM TelemetryDataPoint d")
//    List<TelemetryDataPoint> findAll(List<TelemetryDataPoint> d);
    

}
