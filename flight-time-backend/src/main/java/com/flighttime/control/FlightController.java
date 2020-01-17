package com.flighttime.control;

import java.util.ArrayList;
import java.util.List;

import com.flighttime.flight.AirportV2;
import com.flighttime.flight.Flight;
import com.flighttime.app.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {
    private static Logger logger = LoggerFactory.getLogger(FlightController.class);

    List<AirportV2> airports = new ArrayList<>();

    public List<AirportV2> getAirports() {
        if (airports.isEmpty()) {
            airports = Utils.jsonProcessing();
        }
        return airports;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/flight")
    public Flight flight(@RequestParam(value = "dep", defaultValue = "") String dep,
                         @RequestParam(value = "arr", defaultValue = "") String arr) {

        AirportV2 depAirport = findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info( "flight: " + flight.toString());
        return flight;
    }

    @RequestMapping("/duration")
    public double dist(@RequestParam(value = "dep", defaultValue = "") String dep,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
        AirportV2 depAirport = findAirportByCode(getAirports(), dep);
        AirportV2 arrAirport = findAirportByCode(getAirports(), arr);

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info( "duration: " + flight.getDuration());
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