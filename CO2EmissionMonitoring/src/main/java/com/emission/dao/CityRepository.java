package com.emission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emission.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

    public City findCityByName(String name);
    public City findCityById(Long id);
}
