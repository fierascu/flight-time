package com.flighttime.controller;

import com.flighttime.model.Airport;
import com.flighttime.model.Flight;
import com.flighttime.repository.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.flighttime.repository.AirportRepository.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {
    private static Logger logger = LoggerFactory.getLogger(FlightController.class);

    List<Airport> airports = new ArrayList<>();

    public List<Airport> getAirports() {
        if (airports.isEmpty()) {
            airports = AirportRepository.csvProcessing();
        }
        return airports;
    }

    @RequestMapping("/flight")
    public Flight flight(@RequestParam(value = "dep", defaultValue = "") String dep,
                         @RequestParam(value = "arr", defaultValue = "") String arr) {

        Airport depAirport = findAirportByCode(getAirports(), dep);
        Airport arrAirport = findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info("flight: " + flight.toString());
        return flight;
    }

    @RequestMapping("/duration")
    public double dist(@RequestParam(value = "dep", defaultValue = "") String dep,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
        Airport depAirport = findAirportByCode(getAirports(), dep);
        Airport arrAirport = findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info("duration: " + flight.getDuration());
        return flight.getDuration();
    }


    @RequestMapping("/ap")
    public List<Airport> getAirport(@RequestParam(value = "code", defaultValue = "") String code,
                                    @RequestParam(value = "icao", defaultValue = "") String icao,
                                    @RequestParam(value = "name", defaultValue = "") String name,
                                    @RequestParam(value = "wildcard", defaultValue = "") String wildcard) {
        List<Airport> aps = new ArrayList<>();

        if (!wildcard.isEmpty()) {
            return getAirportWildcard(wildcard);
        }

        if (!code.isEmpty()) {
            aps.add(findAirportByCode(getAirports(), code));
        }
        if (!icao.isEmpty()) {
            aps.add(findAirportByIcao(getAirports(), icao));
        }
        if (!name.isEmpty()) {
            aps.add(findAirportContainingName(getAirports(), name));
        }

        logger.info("airports: " + aps);
        return aps;
    }

    public List<Airport> getAirportWildcard(String wildcard) {
        List<Airport> aps = new ArrayList<>();
        aps.addAll(findAirportWildcard(getAirports(), wildcard));
        aps.removeAll(Collections.singleton(null));
        logger.info("airports: " + aps);
        return aps;
    }

}