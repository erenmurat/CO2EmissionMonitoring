package com.emission.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emission.exception.NotFoundException;
import com.emission.model.CO2Emission;
import com.emission.model.District;
import com.emission.model.Sensor;
import com.emission.service.CO2EmissionService;
import com.emission.service.CityService;
import com.emission.service.DistrictService;

@RestController
public class CO2EmissionController {

	@Autowired
	private CO2EmissionService co2EmissionService;
	@Autowired
	private DistrictService districtService;
    @Autowired
	private CityService cityService;
	

	@RequestMapping(value = "/emission/district/{districtId}", method = RequestMethod.GET)
	public @ResponseBody List<CO2Emission> getEmissionValues(@PathVariable final long districtId) {
		return co2EmissionService.getCO2EmissionByDistrict(districtService.getDistrictById(districtId));

	}

	@RequestMapping(value = "/emission/city/{cityName}", method = RequestMethod.GET)
	public @ResponseBody List<CO2Emission> getEmissionValues(@PathVariable final String cityName)
			throws NotFoundException {
		List<District> districtList = cityService.findCity(cityName).getDistrictList();

		List<CO2Emission> list = new ArrayList<CO2Emission>();
		for (District district : districtList)
			list.addAll(co2EmissionService.getCO2EmissionByDistrict(districtService.getDistrictById(district.getId())));
		return list;
	}

	@PostMapping("/saveEmission/{districtId}/{sensorId}")
	public String saveEmission(@RequestBody final CO2Emission co2Emission, 
			@PathVariable final long districtId,
			@PathVariable final long sensorId) {
		 Sensor sensor;
		District district = districtService.getDistrictById(districtId);
		if(!district.equals(null)) {
		sensor = district.getSensorList()
				.stream()
				.filter(x-> x.getId()==sensorId)
				.findFirst().get();
		if(!sensor.equals(null)) {
			sensor.setDistrict(district);
			co2Emission.setSensor(sensor);		
			return co2EmissionService.saveCO2Emission(co2Emission);
		  
		}else {
			return "Sensor not exists...";
		}
		}else{
			return "District not exists...";
		}
	}

}
