package com.emissiontest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Date;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.emission.dao.CityRepository;
import com.emission.dao.SensorRepository;
import com.emission.exception.NotFoundException;
import com.emission.model.CO2Emission;
import com.emission.model.City;
import com.emission.model.District;
import com.emission.model.Sensor;
import com.emission.service.CO2EmissionService;
import com.emission.service.CityService;
import com.emission.service.DistrictService;

public class TestGeneral {

	@Mock
	private DistrictService districtService;
	@Mock
	private CityService cityService;
	@Mock
	private CityRepository cityRepository;
	@Mock
	private CO2EmissionService co2Service;
	@Mock
	SensorRepository sensorRepository;
	@Mock
	private CO2EmissionService co2EmissionService;

	@BeforeMethod(alwaysRun = true)
	public final void setupMockito() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testWhenListDistrictgetCity() throws NotFoundException {
		City city = new City();
		city.setId(1L);
		city.setName("Wien");
		District district = new District();
		district.setName("Währing");
		district.setCity(city);
		District d1 = new District();
		d1.setName("Penzing");
		d1.setCity(city);
		city.getDistrictList().add(d1);
		city.getDistrictList().add(district);

		Mockito.when(cityService.findCity("Wien")).thenReturn(city);
		assertThat(cityRepository.save(city), is("Saved successfully..."));
	}

	@Test
	public void testWhenCo2EmissionSavedSuccessfully() throws NotFoundException {
		City city = new City();
		city.setId(1L);
		city.setName("Wien");
		District district = new District();
		district.setName("Währing");
		district.setCity(city);
		District d1 = new District();
		d1.setName("Penzing");
		d1.setCity(city);
		city.getDistrictList().add(d1);
		city.getDistrictList().add(district);
		Mockito.when(cityService.findCity("Wien")).thenReturn(city);
		assertThat(cityRepository.save(city), is("Saved successfully..."));
		Sensor sensor = new Sensor();
		sensor.setId(2L);
		sensor.setDistrict(district);
		sensor.setSensorname("Generated");
		sensorRepository.save(sensor);

		List<City> cityList = cityRepository.findAll();

		CO2Emission co2emission = new CO2Emission();
		co2emission.setId(5L);
		co2emission.setSensor(sensor);
		co2emission.setValue("250");
		co2emission.setCreateDatetime(new Date());

		Mockito.when(co2EmissionService.getCO2EmissionByDistrict(district).get(1)).thenReturn(co2emission);
		assertThat(co2EmissionService.saveCO2Emission(co2emission), is("Saved successfully..."));

	}
}
