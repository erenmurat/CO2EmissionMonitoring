package com.emission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emission.dao.DistrictRepository;
import com.emission.model.District;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository ;

    @Override
    public void saveDistrict(List<District> districtList) {
    	districtRepository.saveAll(districtList);
    }

    @Override
    public List<District> getDistricts() {
        return districtRepository.findAll();
    }
    
    @Override
    public District getDistrictById(long districtId) {
    	return districtRepository.findDistrictById(districtId);
    }

	@Override
	public District getDistrictByName(String districtName) {
		 return districtRepository.findDistrictByName(districtName);
	}
}
