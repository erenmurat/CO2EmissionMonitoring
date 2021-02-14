package com.emission.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emission.dao.SensorRepository;
import com.emission.model.Sensor;

@Service
public class SensorServiceImpl implements SensorService {

	@Autowired
	private SensorRepository sensorRepository;

	@Override
	public void addSendor(Sensor sensor) {
		sensorRepository.save(sensor);
	}

	@Override
	public List<Sensor> getAllSensors() {
		return sensorRepository.findAll();
	}

	@Override
	public List<Sensor> findAllSensorsByDistrict(long districtId) {
		List<Sensor> list = sensorRepository.findAll().stream()
				.filter(p -> p.getDistrict().getId().equals(districtId)).collect(Collectors.toList());
		return list;
	}

}
