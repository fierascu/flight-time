package com.flighttime.flighttimebackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FlightControllerTest {

  @Test
  public void multiplicationOfZeroIntegersShouldReturnZero() {
    FlightController tester = new FlightController(); // MyClass is tested

    // assert statements
//    assertEqualstester.multiply(10, 0), "10 x 0 must be 0");
    assertTrue(tester.jsonProcessing());
  }
}