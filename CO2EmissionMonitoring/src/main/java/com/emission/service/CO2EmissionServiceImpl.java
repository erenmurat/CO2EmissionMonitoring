package com.emission.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emission.dao.CO2EmissionRepository;
import com.emission.model.CO2Emission;
import com.emission.model.District;
@Service
public class CO2EmissionServiceImpl implements CO2EmissionService {

	@Autowired
	CO2EmissionRepository co2EmissionRepo;

	@Override
	public List<CO2Emission> getCO2EmissionByDistrict(District district) {
		 
		List<CO2Emission> list =
				co2EmissionRepo.findAll().stream()
		.filter(p -> p.getSensor().getDistrict().getId().equals(district.getId())).collect(Collectors.toList());
		 return list;
	}

	@Override
	public String saveCO2Emission(CO2Emission co2Emission) {
		co2EmissionRepo.save(co2Emission);
		return  "Saved successfully...";
	}
}
