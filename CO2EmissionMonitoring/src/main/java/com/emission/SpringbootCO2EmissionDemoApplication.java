package com.emission;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emission.dao.CO2EmissionRepository;
import com.emission.dao.CityRepository;
import com.emission.dao.DistrictRepository;
import com.emission.dao.SensorRepository;
import com.emission.model.CO2Emission;
import com.emission.model.City;
import com.emission.model.District;
import com.emission.model.Sensor;

@SpringBootApplication
public class SpringbootCO2EmissionDemoApplication implements CommandLineRunner {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private CO2EmissionRepository co2EmissionRepository;
	@Autowired
	private SensorRepository sensorRepository;

	public static void main(String[] args) {

		SpringApplication.run(SpringbootCO2EmissionDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Wien: Währing, Penzing
		// München: Maxvorstadt
		// Barcelona: Gràcia, Eixample

		City city2 = new City();
		city2.setName("München");

		District district1 = new District();
		district1.setName("Maxvorstadt");

		district1.setCity(city2);

		city2.getDistrictList().add(district1);
		cityRepository.save(city2);
	

		City city = new City();
		city.setName("Wien");

		District district = new District();
		district.setName("Währing");

		district.setCity(city);

		District d1 = new District();
		d1.setName("Penzing");

		d1.setCity(city);

		city.getDistrictList().add(d1);
		city.getDistrictList().add(district);

		cityRepository.save(city);

		List<City> cityList = cityRepository.findAll();
		setDefaultSensor(cityList);
		List<Sensor> sensorList = sensorRepository.findAll();
		setDefaultEmission(sensorList);
	}

	final Random ran = new Random();

	private void setDefaultSensor(final List<City> cityList) {
		for (City city : cityList)
			for (District district : city.getDistrictList()) {
				int y = ran.nextInt(10);
				for (int i = 0; i < y; i++) {
					Sensor sensor = new Sensor();
					sensor.setDistrict(district);
					sensor.setSensorname(String.format("%s-v.%d", district.getName(), i));
					sensorRepository.save(sensor);
				}
			}
	}

	private void setDefaultEmission(final List<Sensor> sensorList) {

		for (Sensor sensor : sensorList) {
			int y = ran.nextInt(10);
			for (int i = 0; i < y; i++) {
				CO2Emission emission = new CO2Emission();
				emission.setValue(String.valueOf(ran.nextInt(250)));
				emission.setCreateDatetime(new Date());
				emission.setSensor(sensor);
				co2EmissionRepository.save(emission);
			}
		}
	}
}
