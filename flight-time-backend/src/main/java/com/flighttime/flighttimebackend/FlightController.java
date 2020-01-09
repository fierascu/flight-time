package com.flighttime.flighttimebackend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {
  List<AirportV2> airports = new ArrayList<>();

  @RequestMapping("/flight")
  public Flight flight(@RequestParam(value = "dep", defaultValue = "") String dep,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
    if (airports.isEmpty()) {
      airports = Utils.jsonProcessing();
    }

    AirportV2 depAirport = findAirportByCode(airports, dep);
    AirportV2 arrAirport = findAirportByCode(airports, arr);

    return new Flight(depAirport, arrAirport);
  }

  public AirportV2 findAirportByCode(List<AirportV2> airports, String code) {
    return airports.stream().filter(a -> a.getCode().equalsIgnoreCase(code)).findFirst().orElse(new AirportV2());
  }

}