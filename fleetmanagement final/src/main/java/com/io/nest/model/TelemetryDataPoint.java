package com.io.nest.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryDataPoint {
    
    @Id
    public String deviceId;
    public double sysPressure;
    public double diaPressure;
      
    public String serialize() {
      Gson gson = new Gson();
      return gson.toJson(this);
    }

}
