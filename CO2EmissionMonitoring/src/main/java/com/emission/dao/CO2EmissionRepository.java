package com.emission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emission.model.CO2Emission;
 
@Repository
public interface CO2EmissionRepository extends JpaRepository<CO2Emission , Long> {

 //public List<CO2Emission> getAllCO2Emission(int distict);
}
