package com.flighttime.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.flighttime.config.YAMLConfig;

public class Client {

  private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

  @Autowired
  private static YAMLConfig myConfig;

  public static void main(String[] args) {
    String response = getResponse("VIE", "BUD", "flight");
    logger.info(response);

    String response2 = getResponse("VIE", "BUD", "duration");
    logger.info(response2);
  }

  private static String getResponse(String depAirportCode, String arrAirportCode, String endpoint) {
    try {
      String protocol = YAMLConfig.getProtocol();
      String host = YAMLConfig.getHost();
      int port = YAMLConfig.getPort();

      String url =
          protocol + "://" + host + ":" + port + "/" + endpoint + "?dep=" + depAirportCode + "&arr=" + arrAirportCode;

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
      logger.error(e.getMessage());
    }

    return null;
  }
}

