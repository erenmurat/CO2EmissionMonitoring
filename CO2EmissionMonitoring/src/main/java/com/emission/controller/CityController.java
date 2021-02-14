package com.emission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emission.model.City;
import com.emission.service.CityService;

@RestController
public class CityController {

    @Autowired
    CityService cityService ;

    
    @PostMapping("/saveCity")
    public String saveCity(@RequestBody City city){
    	cityService.getCities()
    	.stream()
    	.filter(x->x.getName().equalsIgnoreCase(city.getName()))
    	.findFirst()
    	.ifPresent(a -> {throw new IllegalArgumentException("City Exists"); } );
    	 
    	cityService.saveCity(city);
        return "Saved successfully...";
    }

    @RequestMapping(value = "/cityList", method = RequestMethod.GET)
    public @ResponseBody List<City> getCities(){
        return cityService.getCities();
    }

}
