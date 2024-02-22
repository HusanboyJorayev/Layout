package com.example.layout.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApi {

    @Bean
    public GroupedOpenApi airlineApi() {
        return GroupedOpenApi.builder()
                .group("Airline")
                .pathsToMatch("api/v/airline")
                .build();
    }

    @Bean
    public GroupedOpenApi airportApi() {
        return GroupedOpenApi.builder()
                .group("Airport")
                .pathsToMatch("api/v/airport")
                .build();
    }

    @Bean
    public GroupedOpenApi baggageApi() {
        return GroupedOpenApi.builder()
                .group("Baggage")
                .pathsToMatch("api/v/baggage")
                .build();
    }

    @Bean
    public GroupedOpenApi baggageCheckApi() {
        return GroupedOpenApi.builder()
                .group("Baggage_Check")
                .pathsToMatch("api/v/baggageCheck")
                .build();
    }

    @Bean
    public GroupedOpenApi boardingPassApi() {
        return GroupedOpenApi.builder()
                .group("Boarding_Pass")
                .pathsToMatch("api/v/boarding")
                .build();
    }

    @Bean
    public GroupedOpenApi bookingApi() {
        return GroupedOpenApi.builder()
                .group("Booking")
                .pathsToMatch("api/v/booking")
                .build();
    }

    @Bean
    public GroupedOpenApi flightManifestApi() {
        return GroupedOpenApi.builder()
                .group("Flight_Manifest")
                .pathsToMatch("api/v/flightManifest")
                .build();
    }

    @Bean
    public GroupedOpenApi flightsApi() {
        return GroupedOpenApi.builder()
                .group("Flights")
                .pathsToMatch("api/v/flights")
                .build();
    }

    @Bean
    public GroupedOpenApi fnoFlyListApi() {
        return GroupedOpenApi.builder()
                .group("No_Fly_List")
                .pathsToMatch("api/v/noFlyList")
                .build();
    }

    @Bean
    public GroupedOpenApi passengersApi() {
        return GroupedOpenApi.builder()
                .group("Passengers")
                .pathsToMatch("api/v/passengers")
                .build();
    }

    @Bean
    public GroupedOpenApi securityCheckApi() {
        return GroupedOpenApi.builder()
                .group("Security_Check")
                .pathsToMatch("api/v/securityCheck")
                .build();
    }
}
