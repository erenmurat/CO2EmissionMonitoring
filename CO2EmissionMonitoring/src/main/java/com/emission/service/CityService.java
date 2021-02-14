package com.emission.service;

import java.util.List;

import com.emission.exception.NotFoundException;
import com.emission.model.City;

public interface CityService {

    public void saveCity(City city);
    public List<City> getCities();
    public City findCity(String name) throws NotFoundException;
    public City findCity(Long  cityId) throws NotFoundException; 
}
