package com.emission.service;

import java.util.List;

import com.emission.model.CO2Emission;
import com.emission.model.City;
import com.emission.model.District;
 
public interface CO2EmissionService {

	public String saveCO2Emission(CO2Emission co2Emission);
	public List<CO2Emission> getCO2EmissionByDistrict( District district);
}
