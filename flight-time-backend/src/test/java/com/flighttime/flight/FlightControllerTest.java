package com.flighttime.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.flighttime.app.Utils;
import com.flighttime.control.FlightController;
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
    assertFalse(Utils.jsonProcessing().isEmpty());
    assertEquals(3885, Utils.jsonProcessing().size());

    logger.info("jsonCanLoaded: ok");
  }

  @Test
  public void jsonFindAirport() {
    FlightController testerFlightController = new FlightController();
    List<AirportV2> airports = Utils.jsonProcessing();
    AirportV2 airport = testerFlightController.findAirportByCode(airports, "VIE");
    assertEquals("VIE", airport.getCode());
    assertEquals("Vienna Schwechat International Airport", airport.getName());
    logger.info("jsonFindAirport: airport: " + airport);
  }

  @Test
  public void jsonFindAirportNegative() {
    FlightController testerFlightController = new FlightController();
    List<AirportV2> airports = Utils.jsonProcessing();
    AirportV2 airport = testerFlightController.findAirportByCode(airports, "NOT_EXISTING");

    logger.info("jsonFindAirportNegative: not found" + airport);
  }
}