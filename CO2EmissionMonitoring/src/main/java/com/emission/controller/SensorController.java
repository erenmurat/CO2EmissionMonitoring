package com.emission.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emission.model.City;
import com.emission.model.District;
import com.emission.model.Sensor;
import com.emission.service.CO2EmissionService;
import com.emission.service.CityService;
import com.emission.service.DistrictService;
import com.emission.service.SensorService;

@RestController
public class SensorController {

	@Autowired
	private SensorService sensorService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	CO2EmissionService co2EmissionService;
	@Autowired
	CityService cityService;

	@RequestMapping(value = "/sensorList", method = RequestMethod.GET)
	public @ResponseBody List<Sensor> getSensors() {
		return sensorService.getAllSensors();
	}

	@RequestMapping(value = "/sensor/district/{districtId}", method = RequestMethod.GET)
	public @ResponseBody List<Sensor> getSensorByDistrictId(@PathVariable final long districtId) {
		return sensorService.findAllSensorsByDistrict(districtId);
	}

	@RequestMapping(value = "/sensor/district", method = RequestMethod.GET)
	public @ResponseBody List<Sensor> getSensorByDistrictName(@RequestParam final String districtName) {
		District district = districtService.getDistrictByName(districtName);
		return district.getSensorList();

	}

	@RequestMapping(value = "/sensor/city", method = RequestMethod.GET)
	public @ResponseBody List<Sensor> getSensorsByCityName(@RequestParam String cityName) {
		try {
            City city = cityService.findCity(cityName);
			List<Sensor> listSensor = new ArrayList<Sensor>();
			for (District district : city.getDistrictList())
				listSensor.addAll(district.getSensorList());
			return listSensor;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
