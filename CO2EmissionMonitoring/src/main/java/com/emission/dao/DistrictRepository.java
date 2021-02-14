package com.emission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emission.model.City;
import com.emission.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
	 public District findDistrictById(Long id);
	 public District findDistrictByName(String name);
	  
}
