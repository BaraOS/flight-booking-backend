# Flight Booking App using Spring Boot and Amadeus Java Api

## Getting Started
To make your first API call, you will need to [register](https://developers.amadeus.com/register) for an Amadeus Developer Account and [set up your first application](https://developers.amadeus.com/my-apps).

## Prerequisites
- Java 17 or later
- Maven 3.6.3 or later

## Stack
- Spring Boot
- [Amadeus Java Api](https://github.com/amadeus4dev/amadeus-java)

## Build
`./mvnw clean install`
or
`mvn clean install`

## Run
**On Linux/MacOS**  
`export AMADEUS_CLIENT_ID="Your Client ID"`  
`export AMADEUS_CLIENT_SECRET="Your Secret ID"`  
`./mvnw spring-boot:run` or `mvn spring-boot:run`  

**On Windows**  
`set AMADEUS_CLIENT_ID="Your Client ID"`  
`set AMADEUS_CLIENT_SECRET="Your Secret ID"`  
`./mvnw spring-boot:run` or `mvn spring-boot:run`  
