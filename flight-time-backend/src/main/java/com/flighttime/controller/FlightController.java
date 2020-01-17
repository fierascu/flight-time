package com.flighttime.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flighttime.model.AirportV2;
import com.flighttime.model.Flight;
import com.flighttime.repository.AirportRepositoy;

@RestController
public class FlightController {
    List<AirportV2> airports = new ArrayList<>();

    public List<AirportV2> getAirports() {
        if (airports.isEmpty()) {
            airports = AirportRepositoy.jsonProcessing();
        }
        return airports;
    }

    @GetMapping("/flight")
    public Flight flight(@RequestParam(value = "dep", defaultValue = "") String dep,
                         @RequestParam(value = "arr", defaultValue = "") String arr) {
        AirportV2 depAirport = AirportRepositoy.findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = AirportRepositoy.findAirportByCode(getAirports(), arr);

        return new Flight(depAirport, arrAirport);
    }

    @GetMapping("/duration")
    public double dist(@RequestParam(value = "dep", defaultValue = "") String dep,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
        AirportV2 depAirport = AirportRepositoy.findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = AirportRepositoy.findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        return flight.getDuration();
    }

    @GetMapping("/ap")
    public List<AirportV2> getAirport(@RequestParam(value = "code", defaultValue = "") String code,
                                      @RequestParam(value = "icao", defaultValue = "") String icao,
                                      @RequestParam(value = "name", defaultValue = "") String name) {
        List<AirportV2> aps = new ArrayList<>();

        if (!code.isEmpty()) {
            aps.add(AirportRepositoy.findAirportByCode(getAirports(), code));
        }
        if (!icao.isEmpty()) {
            aps.add(AirportRepositoy.findAirportByIcao(getAirports(), icao));
        }
        if (!name.isEmpty()) {
            aps.add(AirportRepositoy.findAirportContainingName(getAirports(), name));
        }

        return aps;
    }

}