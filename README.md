# A comprehensive example on microservices using spring-boot with spring-cloud

It is a project for airport service. 

In this project we have created six microservices for airport officials and airport passengers. These microservices provides services from passenger's ticket booking to passenger's boarding.

It has two more microservices one for eureka server and one for api gateway.

<br/>

|Microservice|Purpose|Note|     
|----|-----|-----|     
|**airport-pilot-service**|<li>It is a microservice for airport officials</li><li>It is having APIs for PILOT such as createPilot, getPilotById, getAllPilots, updatePilot, deletePilot, findPilotsByDesignation & flushCache</li>|
|**airport-flight-service**|<li>It is a microservice for airport officials</li><li>It is having APIs for FLIGHT such as createFlight, getFlightById, updateFlight, deleteFlight & searchFlightBySourceDestinationAndDate|This microservice internally calls airport-pilot-service using OpenFiegn to get PILOT details.</li>|
|**airport-booking-service**|<li>It is a microservice for airport flight passengers & airport officials</li><li>It is having APIs for BOOKING such as createBooking, getBookingById & findBookingByPNRNumber|This microservice internally calls airport-flight-service using OpenFiegn to get FLIGHT details.</li>|
|**airport-security-service**|<li>It is a microservice for airport officials</li><li>It is having APIs for airport SECURITY check such as createSecurity, getSecurityById & findSecurityByBookingId</li>|
|**airport-checkin-service**|<li>It is a microservice for airport officials</li><li>It is having APIs for airport CHECKIN service such as createCheckin, getCheckinById & findCheckinByBookingId</li>|
|**airport-boarding-service**|<li>It is a microservice for airport officials</li><li>It is having APIs for BOARDING findBoardingByBookingId|This microservice internally calls airport-booking-service, airport-checkin-service, airport-security-service using OpenFiegn to get BOOKING, CHECKIN, SECURITY CHECK details.</li>|
|**eureka-server**|It is a microservice for service discovery and registry|
|**api-gateway**|It is a microservice work as a gateway (single entry point) for all microservices|


## Microservice Architecture
![microservices-architecture](./snapshots/microservices-architecture.png)

## Technology Stack
* **Spring Boot** (Starter)
	* **Web**
		* <sub>RESTful API <sup>[link1](./airport-pilot-service/src/main/java/com/airport/pilot/controller/PilotController.java), [link2](./airport-pilot-service/src/main/java/com/airport/pilot/service/PilotServiceImpl.java), [link3](./airport-pilot-service/src/main/java/com/airport/pilot/request/Pilot.java), [link4](./airport-pilot-service/src/main/java/com/airport/pilot/response/PilotResponse.java), [link5](./airport-pilot-service/src/main/resources/application.yml), [link6](./airport-pilot-service/pom.xml)</sup></sub>
		* <sub>@ControllerAdvice (Exception Handling)</sub> 
			* <sub>global <sup>[link](./airport-pilot-service/src/main/java/com/airport/pilot/exception/PilotControllerAdvicer.java)</sup></sub>
			* <sub>custom <sup>[link1](./airport-pilot-service/src/main/java/com/airport/pilot/exception/PilotCustomException.java), [link2](./airport-pilot-service/src/main/java/com/airport/pilot/service/PilotServiceImpl.java)</sup></sub>
		* <sub>SLF4J with Logback (Logging) <sup>[link1](./airport-checkin-service/src/main/resources/application.yml), [link2](./airport-checkin-service/src/main/java/com/airport/checkin/service/CheckinServiceImpl.java)</sup></sub>
			* <sub>ANSIConsole plugin (colorful Logs) <sup>[link1](./snapshots/ansi%20console%20plugin%20installation.PNG), [link2](./snapshots/ansi%20console%20properties%20and%20display%20color%20output%20logs.PNG)</sup></sub>
	* **Test**
		* <sub>JUnit (Unit Testing)</sub>
			* <sub>for rest controller <sup>[link](./airport-pilot-service/src/test/java/com/airport/pilot/controller/PilotControllerTest.java)</sup></sub>
		* <sub>Jacoco (Code Coverage) <sup>[link1](./airport-pilot-service/pom.xml), [link2](./snapshots/how%20to%20run%20code%20coverage.PNG), [link3](./snapshots/output%20of%20code%20coverage.PNG)</sup></sub>
	* **Data JPA** <sub><sup>[link1](./airport-pilot-service/src/main/java/com/airport/pilot/repository/PilotRepository.java), [link2](./airport-pilot-service/src/main/java/com/airport/pilot/entity/PilotEntity.java)</sup></sub>
	* **Actuator** <sub><sup>[link1](./airport-pilot-service/pom.xml), [link2](./airport-pilot-service/src/main/resources/application.yml), [link3](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html), [link4](./snapshots/actuator-output1.PNG), [link5](./snapshots/actuator-output2.PNG), [link6](./snapshots/actuator-output3.PNG), [link7](./snapshots/actuator-output4.PNG), [link8](./snapshots/actuator-output5.PNG), [link9](./snapshots/actuator-output6.PNG), [link10](./snapshots/actuator-output7.PNG), [link11](./snapshots/actuator-output8.PNG)</sup></sub>
		* <sub>CORS enabled <sup>[link1](./airport-pilot-service/src/main/resources/application.yml), [link2](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)</sup></sub>
		* **Prometheus & Grafana** <sub><sup>[link1](./snapshots/prometheus%20&%20grafana%20setup.txt), [link2](./snapshots/prometheus%20server%20console.PNG), [link3](./snapshots/grafana%20server%20console.PNG), [link4](./airport-pilot-service/pom.xml), [link5](./airport-pilot-service/src/main/resources/application.yml)</sup></sub>
	* **Mail** <sub><sup>[link1](./airport-bookings-service/pom.xml), [link2](./airport-bookings-service/src/main/resources/application.yml), [link3](./airport-bookings-service/src/main/java/com/airport/bookings/email/BookingEmailService.java)</sup></sub>
* **Spring Cloud** (Starter)
	* **Eureka Server** <sub><sup>[link1](./eureka-server/pom.xml), [link2](./eureka-server/src/main/resources/application.yml), [link3](./eureka-server/src/main/java/com/airport/eureka/server/EurekaServerApplication.java)</sup></sub> 
		* <sub>Eureka Clients <sup>[link1](./airport-pilot-service/pom.xml), [link2](./airport-pilot-service/src/main/resources/application.yml), [link3](./airport-pilot-service/src/main/java/com/airport/pilot/AirportPilotServiceApplication.java)</sup></sub>
	* **Gateway** <sub><sup>[link1](./api-gateway/pom.xml), [link2](./api-gateway/src/main/resources/application.yml), [link3](./api-gateway/src/main/java/com/airport/api/gateway/filter/APIGatewayFilter.java)</sup></sub>
		* <sub>`Zuul is deprecated.`</sub> 
		* <sub>It also does load balancing so no need to use separate Spring Cloud Load Balancer. `Ribbon is deprecated.`</sub>
	* **OpenFeign** <sub><sup>[link1](./airport-bookings-service/pom.xml), [link2](./airport-bookings-service/src/main/java/com/airport/bookings/feignclients/FlightFeignClient.java), [link3](./airport-bookings-service/src/main/java/com/airport/bookings/service/BookingServiceImpl.java)</sup></sub>
		* <sub>Its an alternative to WebClient. `RestTemplate is deprecated.`</sub>
	* **Circuit Breaker** 
		* <sub>It provides an abstraction across different circuit breaker implementations i.e. Hystrix, Resilience4J, Sentinel, Spring Retry.</sub>
		* **Resilience4j** <sub><sup>[link1](./airport-flight-service/pom.xml), [link2](./airport-flight-service/src/main/java/com/airport/flight/circuitbreaker/PilotServiceCircuitBreaker.java), [link3](./airport-flight-service/src/main/resources/application.yml)</sup></sub>
		* <sub>`Hystrix is deprecated`</sub>
	* **Sleuth** <sub><sup>[link1](./api-gateway/pom.xml), [link2](./airport-pilot-service/pom.xml), [link3](/airport-flight-service/pom.xml), [link4](/api-gateway/src/main/resources/application.yml)</sup></sub>
	* **Zipkin** <sub><sup>[link1](./snapshots/start-zipkin-server.PNG), [link2](./snapshots/run-zipkin-server-and-trace-any-sleuth-request.PNG)</sup></sub>
* **Hazelcast** <sub><sup>[link1](./snapshots/hazelcast%20setup.txt), [link2](./airport-pilot-service/pom.xml), [link3](./airport-pilot-service/src/main/resources/hazelcast.yaml), [link4](./airport-pilot-service/src/main/java/com/airport/pilot/service/PilotServiceImpl.java)</sup></sub>
* **OpenAPI** (swagger-ui) <sub><sup>[link1](./airport-pilot-service/pom.xml), [link2](./airport-pilot-service/src/main/resources/application.yml)</sup></sub>
* **Lombok** <sub><sup>[link1](./airport-pilot-service/pom.xml), [link2](./airport-pilot-service/src/main/java/com/airport/pilot/entity/PilotEntity.java)</sup></sub>
* **ModelMapper** <sub><sup>[link1](./airport-pilot-service/pom.xml), [link2](./airport-pilot-service/src/main/java/com/airport/pilot/service/PilotServiceImpl.java)</sup></sub>
* **SonarQube** <sub><sup>[link1](./snapshots/sonarqube%20setup.txt), [link2](./snapshots/sonarqube%20server%20output1.PNG), [link3](./snapshots/sonarqube%20server%20output2.PNG), [link4](./snapshots/sonarqube%20server%20output3.PNG), [link5](./snapshots/sonarqube%20server%20output4.PNG)</sup></sub>
* **Maven**
* **JDK 11**
* **Eclipse IDE**
* **MySQL RDBMS**
* **Docker** <sub><sup>[link1](./airport-pilot-service/Dockerfile), [link2](./snapshots/how%20to%20build%20and%20push%20docker%20images.PNG), [link3](./snapshots/how%20to%20build%20and%20push%20docker%20image%20without%20creating%20Dockerfile.PNG), [link4](./snapshots/docker-desktop-console.png), [link5](./snapshots/docker-hub-output.png)</sup></sub>


## HowTo
**Step 1.** Database Setup (you can use MySQL Workbench)
* create schema `CREATE DATABASE airportdb`
* create tables by running dump file [airportdb.sql](https://github.com/SirajChaudhary/comprehensive-example-on-microservices-using-spring-boot-with-spring-cloud/blob/master/airportdb.sql) 

**Step 2.** Configure lombok for your eclipse IDE (i.e. run lombok.jar)

**Step 3.** Clone the project
> git clone https://github.com/SirajChaudhary/comprehensive-example-on-microservices-using-spring-boot-with-spring-cloud.git

**Step 4.** Import into your eclipse IDE

**Step 5.** Update following properties
* update your database credentials in the .properties file wherever it is used in all microservices.
* update YOUR_GMAIL_USERNAME/YOUR_GMAIL_PASSWORD in the [.properties file](./airport-bookings-service/src/main/resources/application.yml) of airport-booking-service.  

**Step 6.** Build all microservices `mvn clean install`
<br />- we build in the following sequence and before that we set Java Build Path of each microservice to JDK11
* eureka-server
* api-gateway
* airport-pilot-service
* airport-flight-service
* airport-booking-service
* airport-security-service
* airport-checkin-service
* airport-boarding-service

**Step 7.** Run all microservices in the following sequence.
* eureka-server
* api-gateway
* all microservices one by one in any sequence

**Step 8.** Run APIs using Postman or OpenAPI (Swagger-UI)
<br />- APIs should be run as per the following sequence of the microservices for the first time.
* airport-pilot-service
* airport-flight-service
* airport-booking-service
* airport-security-service
* airport-checkin-service
* airport-boarding-service

**Step 9 (optional).** Run Zipkin Server to trace Sleuth logs on GUI.
* download and install zipkin server and then [run the zipkin server](./snapshots/start-zipkin-server.PNG). Here is the [zipkin server console output](./snapshots/run-zipkin-server-and-trace-any-sleuth-request.PNG)

## Snapshots
### `Eureka Server`
**http://localhost:8761/**
![http://localhost:8761/](./snapshots/eureka-server-console.PNG)


### `OpenAPI (swagger-ui)`
It will be used just for testing purpose. It won't route via API Gateway. We run like an independent swagger-ui of each microservice.

* [http://localhost:8081/swagger-ui.html](./snapshots/airport-pilot-service-swagger-ui.PNG)

* [http://localhost:8082/swagger-ui.html](./snapshots/airport-flight-service-swagger-ui.PNG)

* [http://localhost:8083/swagger-ui.html](./snapshots/airport-booking-service-swagger-ui.PNG)

* [http://localhost:8084/swagger-ui.html](./snapshots/airport-security-service-swagger-ui.PNG)

* [http://localhost:8085/swagger-ui.html](./snapshots/airport-checkin-service-swagger-ui.PNG)

* [http://localhost:8086/swagger-ui.html](./snapshots/airport-boarding-service-swagger-ui.PNG)



### `Postman`
API calls will route via API Gateway (2021) only!

* [An API to create a new pilot](./snapshots/airport-pilot-service-create-new-pilot.PNG) <br />
> **URL:** localhost:2021/airport-pilot-service/api/v1/pilots <br />
**Method:** POST <br />
**RequestBody:** {
    "name": "Ashutosh Sharma",
    "designation": "Sr. Captain",
    "experience": "22 Year"
}

* [An API to create a new flight](./snapshots/airport-flight-service-create-new-flight.PNG) <br />
> **URL:** localhost:2021/airport-flight-service/api/v1/flights <br />
**Method:** POST <br />
**RequestBody:** {
  "vendor": "AirIndia",
  "source": "Delhi",
  "destination": "London",
  "departureDate": "08/08/2021",
  "departureTime": "10:15pm",
  "status": "On Time",
  "pilotId": 1
}

* [An API to create a new booking](./snapshots/airport-booking-service-create-new-booking.PNG) <br />
> **URL:** localhost:2021/airport-booking-service/api/v1/bookings <br />
**Method:** POST <br />
**RequestBody:** {
  "fullname": "Bill Gates",
  "mobile": "9100800123",
  "email": "mr.billgates@yahoo.com",
  "address": "stree1, adambakham",
  "flightNumber": 1
}

* [An API to create a new security check](./snapshots/airport-security-service-create-new-security.PNG) <br />
> **URL:** localhost:2021/airport-security-service/api/v1/securities <br />
**Method:** POST <br />
**RequestBody:** {
    "identityProof": "passport",
    "covidReport": "-ve",
    "ctScan": "done",
    "status": "done",
    "bookingId": "1"
}

* [An API to create a new checkin](./snapshots/airport-checkin-service-create-new-checkin.PNG) <br />
> **URL:** localhost:2021/airport-checkin-service/api/v1/checkin <br />
**Method:** POST <br />
**RequestBody:** {
  "checkInBags": "5",
  "cabinBags": "2",
  "bookingId": "1"
}

* [An API to get complete details of a boarding](./snapshots/airport-boarding-service-get-a-boarding-details.PNG) <br />
> **URL:** localhost:2021/airport-boarding-service/api/v1/boardings/find-by-booking-id/1 <br />
**Method:** GET <br />

![output](./snapshots/airport-boarding-service-get-a-boarding-details.PNG)

**Similarly there are a bunch of APIs (e.g. create/read/update/delete/findByXXX) in each microservice which you can explore yourself easily.** 


## License
Free Software, by [Siraj Chaudhary](https://www.linkedin.com/in/sirajchaudhary/) 
