package com.emission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emission.model.City;
import com.emission.model.District;
import com.emission.service.CityService;
import com.emission.service.DistrictService;

@RestController
public class DistrictController {

    @Autowired
    DistrictService districtService ;

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/districtList", method = RequestMethod.GET)
    public @ResponseBody List<District> getDistricts(){
        return districtService.getDistricts();
    }

    @PostMapping("/saveDistrict/{cityName}")
    public String saveDistrict(@ModelAttribute List<District> districtList, @PathVariable String cityName){
        try {
            City city = cityService.findCity(cityName.toUpperCase());

            for(District district: districtList)
            	district.setCity(city);

            districtService.saveDistrict(districtList);
            return "<<District saved successfully..<<";
        }catch (Exception ex){
            ex.printStackTrace();
            return "Error in saving District ..";
        }
    }
    
    @RequestMapping(value = "/districtList/city", method = RequestMethod.GET)
    public @ResponseBody List<District> getDistrictsByCityName(@RequestParam String cityName){
    	  try {
              City city = cityService.findCity(cityName);
      return  city.getDistrictList();
          }catch (Exception ex){
              ex.printStackTrace();
              return null;
          }
    }
    
    @RequestMapping(value = "/districtListbyid/{cityId}", method = RequestMethod.GET)
    public @ResponseBody List<District> getDistrictListByCityName(@PathVariable Long cityId){
    	  try {
              City city = cityService.findCity(cityId);
              return  city.getDistrictList();
          }catch (Exception ex){
              ex.printStackTrace();
              return null;
          }
    }
    
}
