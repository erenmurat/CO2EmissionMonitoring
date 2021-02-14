package com.emission.service;

import java.util.List;

import com.emission.model.District;

public interface DistrictService {

    public void saveDistrict(List<District> districtList);

	public List<District> getDistricts();
    
    public District getDistrictById(long districtId);
    
    public District getDistrictByName(String districtName);

}