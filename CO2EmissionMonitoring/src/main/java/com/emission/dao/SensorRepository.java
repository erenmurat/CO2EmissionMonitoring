package com.emission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emission.model.District;
import com.emission.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

	public List<Sensor> getSensorListByDistrict(District district);

}
