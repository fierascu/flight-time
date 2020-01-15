package com.flighttime.controller;

import java.util.ArrayList;
import java.util.List;

import com.flighttime.model.AirportV2;
import com.flighttime.model.Flight;
import com.flighttime.repository.AirportRepositoy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {
    List<AirportV2> airports = new ArrayList<>();

    public List<AirportV2> getAirports() {
        if (airports.isEmpty()) {
            airports = AirportRepositoy.jsonProcessing();
        }
        return airports;
    }

    @RequestMapping("/flight")
    public Flight flight(@RequestParam(value = "dep", defaultValue = "") String dep,
                         @RequestParam(value = "arr", defaultValue = "") String arr) {

        AirportV2 depAirport = findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = findAirportByCode(getAirports(), arr);

        return new Flight(depAirport, arrAirport);
    }

    @RequestMapping("/duration")
    public double dist(@RequestParam(value = "dep", defaultValue = "") String dep,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
        AirportV2 depAirport = findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        return flight.getDuration();
    }


    @RequestMapping("/ap")
    public List<AirportV2> getAirport(@RequestParam(value = "code", defaultValue = "") String code,
                                      @RequestParam(value = "icao", defaultValue = "") String icao,
                                      @RequestParam(value = "name", defaultValue = "") String name) {
        List<AirportV2> aps = new ArrayList<>();

        if (!code.isEmpty()) {
            aps.add(findAirportByCode(getAirports(), code));
        }
        if (!icao.isEmpty()) {
            aps.add(findAirportByIcao(getAirports(), icao));
        }
        if (!name.isEmpty()) {
            aps.add(findAirportContainingName(getAirports(), name));
        }

        return aps;
    }

    public AirportV2 findAirportByCode(List<AirportV2> airports, String code) {
        return airports.stream().filter(a -> a.getCode().equalsIgnoreCase(code)).findFirst().orElse(new AirportV2());
    }

    public AirportV2 findAirportByIcao(List<AirportV2> airports, String icao) {
        return airports.stream().filter(a -> a.getIcao().equalsIgnoreCase(icao)).findFirst().orElse(new AirportV2());
    }

    public AirportV2 findAirportContainingName(List<AirportV2> airports, String name) {
        return airports.stream().filter(a -> a.getName().toLowerCase().contains(name.toLowerCase())).findFirst().orElse(new AirportV2());
    }

}