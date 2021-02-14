package com.emission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emission.dao.CityRepository;
import com.emission.exception.NotFoundException;
import com.emission.model.City;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;

	@Override
	// @Transactional
	public void saveCity(City department) {
		cityRepository.save(department);
	}

	@Override
	public List<City> getCities() {
		return cityRepository.findAll();
	}

	@Override
	public City findCity(String name) throws NotFoundException {
       return	 cityRepository.findCityByName(name);
 }

	@Override
	public City findCity(Long cityId) throws NotFoundException {
		 
		return cityRepository.findCityById(cityId);
	}
}
