package com.flighttime.flighttimebackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightControllerTest {

  private static Logger logger = LoggerFactory.getLogger(FlightControllerTest.class);

  @BeforeAll
  public static void setup() {
    logger.info("startup: loading airports");
  }

  @Test
  public void jsonCanLoaded() {
    FlightController testerFlightController = new FlightController();
    assertFalse(testerFlightController.jsonProcessing().isEmpty());
    assertEquals(3885, testerFlightController.jsonProcessing().size());

    logger.info("jsonCanLoaded: ok");
  }

  @Test
  public void jsonFindAirport() {
    FlightController testerFlightController = new FlightController();
    List<AirportV2> airports = testerFlightController.jsonProcessing();
    AirportV2 airport = testerFlightController.findAirportByCode(airports, "VIE");
    assertEquals("VIE", airport.getCode());
    assertEquals("Vienna Schwechat International Airport", airport.getName());
    logger.info("jsonFindAirport: airport: " + airport);
  }

  @Test
  public void jsonFindAirportNegative() {
    FlightController testerFlightController = new FlightController();
    List<AirportV2> airports = testerFlightController.jsonProcessing();
    AirportV2 airport = testerFlightController.findAirportByCode(airports, "NOT_EXISTING");

    logger.info("jsonFindAirportNegative: not found" + airport);
  }
}