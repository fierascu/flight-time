package com.flighttime.flighttimebackend;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FlightController {

  @RequestMapping("/flight")
  public Flight flight(@RequestParam(value = "dest", defaultValue = "") String dest,
                       @RequestParam(value = "arr", defaultValue = "") String arr) {
    jsonProcessing();
    return new Flight(dest, arr);
  }

  public boolean jsonProcessing() {
    try {
      Resource resource = new ClassPathResource("airports_v2.json");

      InputStream input = resource.getInputStream();
      String jsonString = null;
      try (Scanner scanner = new Scanner(input, StandardCharsets.UTF_8.name())) {
        jsonString = scanner.useDelimiter("\\A").next();
      }

      ObjectMapper mapper = new ObjectMapper();
      List<AirportV2> list = mapper.readValue(jsonString,
          new TypeReference<ArrayList<AirportV2>>() {
          });
      System.out.println(list);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;

  }

}