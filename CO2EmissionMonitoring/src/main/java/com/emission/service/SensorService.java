package com.emission.service;

import java.util.List;

import com.emission.model.Sensor;

public interface SensorService {
	
	public List<Sensor> findAllSensorsByDistrict(long  districtId);
	public void addSendor(Sensor sensor);
	public List<Sensor> getAllSensors();
 	
}
