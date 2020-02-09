package com.flighttime.model;

import com.flighttime.repository.AirportRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightControllerTest {

    private static Logger logger = LoggerFactory.getLogger(FlightControllerTest.class);

    @BeforeAll
    public static void setup() {
        logger.info("startup: loading airports");
    }

    @Test
    public void jsonCanLoaded() {
        assertFalse(AirportRepository.csvProcessing().isEmpty());
        assertEquals(3885, AirportRepository.csvProcessing().size());

        logger.info("jsonCanLoaded: ok");
    }

    @Test
    public void jsonFindAirport() {
        List<Airport> airports = AirportRepository.csvProcessing();
        Airport airport = AirportRepository.findAirportByCode(airports, "VIE");
        assertEquals("VIE", airport.getIata_code());
        assertEquals("Vienna Schwechat International Airport", airport.getName());
        logger.info("jsonFindAirport: airport: " + airport);
    }

    @Test
    public void jsonFindAirportNegative() {
        List<Airport> airports = AirportRepository.csvProcessing();
        Airport airport = AirportRepository.findAirportByCode(airports, "NOT_EXISTING");

        logger.info("jsonFindAirportNegative: not found" + airport);
    }

    @Test
    public void jsonFindFlight() {
        List<Airport> airports = AirportRepository.csvProcessing();
        Airport depAirport = AirportRepository.findAirportByCode(airports, "VIE");
        Airport arrAirport = AirportRepository.findAirportByCode(airports, "BUD");

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info("flight: " + flight.toString());

        assertEquals("0hrs 43mins", flight.getDurationAsTime());
        assertEquals(43, (int) flight.getDuration());
    }


    @Test
    public void jsonFindFlightNegative() {
        List<Airport> airports = AirportRepository.csvProcessing();
        Airport depAirport = AirportRepository.findAirportByCode(airports, null);
        Airport arrAirport = AirportRepository.findAirportByCode(airports, "NOT_EXISTING_AIRPORT");

        final Flight flight = new Flight(depAirport, arrAirport);
        logger.info("flight: " + flight.toString());

        assertNull(depAirport);
        assertNull(arrAirport);
        assertNull(flight.getDurationAsTime());
        assertEquals(0, (int) flight.getDuration());
    }

}