package com.flighttime.flighttimebackend;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

  public static double convertMetersToMiles(double miles) {
    return (miles / 1609.344);
  }

  public static double convertMilesToMeters(double meters) {
    return (meters * 0.000621371192);
  }

  /**
   * result in minutes
   * "30 min plus 1 hour per every 500 miles"
   * src: https://openflights.org/faq
   *
   * @param dist
   * @return
   */
  static double calculateDurationFromMiles(double dist) {
    return 30 + 60 * dist;
  }

  static double calculateDurationFromMeters(double dist) {
    return calculateDurationFromMiles(convertMetersToMiles(dist));
  }

  /**
   * Calculate distance between two points in latitude and longitude taking
   * into account height difference. If you are not interested in height
   * difference pass 0.0. Uses Haversine method as its base.
   * <p>
   * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
   * el2 End altitude in meters
   *
   * @returns Distance in Meters
   */
  public static double calculateDistanceFromPoints(double lat1, double lat2, double lon1,
                                                   double lon2, double el1, double el2) {

    final int R = 6371; // Radius of the earth

    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c * 1000; // convert to meters

    double height = el1 - el2;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    return Math.sqrt(distance);
  }

  public static List<AirportV2> jsonProcessing() {
    List<AirportV2> airports = new ArrayList<>();
    try {
      Resource resource = new ClassPathResource("airports_v2.json");

      InputStream input = resource.getInputStream();
      String jsonString = null;
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

  public static String getDurationAsTime(double minutes) {
    Duration duration = Duration.ofMinutes((long)minutes);
    long hours = duration.toHours();
    long mins = duration.minusHours(hours).toMinutes();
    return String.format("%dhrs %02dmins", hours, mins);
  }
}
