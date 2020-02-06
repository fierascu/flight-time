package com.flighttime.controller;

import com.flighttime.model.AirportV2;
import com.flighttime.model.Flight;
import com.flighttime.repository.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.flighttime.repository.AirportRepository.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {
    private static Logger logger = LoggerFactory.getLogger(FlightController.class);

    List<AirportV2> airports = new ArrayList<>();

    public List<AirportV2> getAirports() {
        if (airports.isEmpty()) {
            airports = AirportRepository.jsonProcessing();
        }
        return airports;
    }

    @RequestMapping("/flight")
    public Flight flight(@RequestParam(value = "dep", defaultValue = "") String dep,
                         @RequestParam(value = "arr", defaultValue = "") String arr) {

        AirportV2 depAirport = findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info("flight: " + flight.toString());
        return flight;
    }

    @RequestMapping("/duration")
    public double dist(@RequestParam(value = "dep", defaultValue = "") String dep,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
        AirportV2 depAirport = findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info("duration: " + flight.getDuration());
        return flight.getDuration();
    }


    @RequestMapping("/ap")
    public List<AirportV2> getAirport(@RequestParam(value = "code", defaultValue = "") String code,
                                      @RequestParam(value = "icao", defaultValue = "") String icao,
                                      @RequestParam(value = "name", defaultValue = "") String name,
                                      @RequestParam(value = "wildcard", defaultValue = "") String wildcard) {
        List<AirportV2> aps = new ArrayList<>();

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

    public List<AirportV2> getAirportWildcard(String wildcard) {
        List<AirportV2> aps = new ArrayList<>();
        aps.add(findAirportWildcard(getAirports(), wildcard));
        logger.info("airports: " + aps);
        return aps;
    }

}