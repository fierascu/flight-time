package com.flighttime.repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flighttime.model.AirportV2;

public class AirportRepositoy {
  public static List<AirportV2> jsonProcessing() {
      List<AirportV2> airports = new ArrayList<>();
      try {
          Resource resource = new ClassPathResource("airports_v2.json");

          InputStream input = resource.getInputStream();
          String jsonString;
          try (Scanner scanner = new Scanner(input, StandardCharsets.UTF_8.name())) {
              jsonString = scanner.useDelimiter("\\A").next();
          }

          ObjectMapper mapper = new ObjectMapper();
          airports = mapper.readValue(jsonString,
                  new TypeReference<ArrayList<AirportV2>>() {
                  });
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
      return airports;
  }
}
