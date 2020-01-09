package com.flighttime.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

  public static void main(String[] args) throws Exception {
    String response = getResponse("VIE", "BUD", "flight");
    System.out.println(response);

    String response2 = getResponse("VIE", "BUD", "duration");
    System.out.println(response2);
  }

  private static String getResponse(String depAirportCode, String arrAirportCode, String endpoint) {
    try {

      // TODO read from application.yml

      String protocol = "http";
      String host = "localhost";
      int port = 8082;

      String url = protocol + "://" + host + ":" + port + "/" + endpoint + "?dep=" + depAirportCode + "&arr=" + arrAirportCode;

      HttpURLConnection httpClient = (HttpURLConnection)new URL(url).openConnection();

      if (httpClient.getResponseCode() == 200) {
        try (BufferedReader in = new BufferedReader(
            new InputStreamReader(httpClient.getInputStream()))) {

          StringBuilder response = new StringBuilder();
          String line;

          while ((line = in.readLine()) != null) {
            response.append(line);
          }
          return response.toString();
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return null;
  }
}

