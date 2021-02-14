#  Welcome CO2EmissionMonitoring app

Spring Boot 2.1.2.RELEASE
Maven
JPA
h2database
Mockito
Testng

In order to test rest service use below curl;
#The district the sensor belongs to
curl http://localhost:8082/sensor/district/1

#The city the district belongs to
curl http://localhost:8082/districtList/city?cityName=Wien

#The city the district belongs to
curl http://localhost:8082/sensor/district?districtName=Penzing

#save City
curl -X POST http://localhost:8082/saveCity \  -H 'cache-control: no-cache' \  -H 'content-type: application/json' \  -d '{"name":"Barcelona"}'

#save Emission
curl -X POST \
  http://localhost:8082/saveEmission/1/1 \
  -H 'cache-control: no-cache' \
  -d '{
 "value": "400",
 "createDatetime": "2021-02-15T18:21:51.088+0000"
}'


#unittest
1- testWhenListDistrictgetCity
2- testWhenCo2EmissionSavedSuccessfully

#SpringbootCO2EmissionDemoApplication
when the context loading , all tables automatically created and fill the sample data.