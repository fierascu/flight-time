package com.flighttime.flighttimebackend;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FlightController {
  List<AirportV2> airports = new ArrayList<>();

  @RequestMapping("/flight")
  public Flight flight(@RequestParam(value = "dep", defaultValue = "") String dep,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
    if (airports.isEmpty()) {
      airports = jsonProcessing();
    }

    AirportV2 depAirport = findAirportByCode(airports, dep);
    AirportV2 arrAirport = findAirportByCode(airports, arr);

    return new Flight(depAirport, arrAirport);
  }

  public List<AirportV2> jsonProcessing() {
    try {
      Resource resource = new ClassPathResource("airports_v2.json");

      InputStream input = resource.getInputStream();
      String jsonString = null;
      try (Scanner scanner = new Scanner(input, StandardCharsets.UTF_8.name())) {
        jsonString = scanner.useDelimiter("\\A").next();
      }

      ObjectMapper mapper = new ObjectMapper();
      airports = mapper.readValue(jsonString,
          new TypeReference<ArrayList<AirportV2>>() {
          });
//      System.out.println(airports);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return airports;

  }

  public AirportV2 findAirportByCode(List<AirportV2> airports, String code) {
    return airports.stream().filter(a -> a.getCode().equalsIgnoreCase(code)).findFirst().orElse(new AirportV2());
  }

}