package com.flighttime.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.flighttime"})
public class FlightTimeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightTimeBackendApplication.class, args);
    }
}
