package com.flighttime.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonReader {

  private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char)cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    try (InputStream is = new URL(url).openStream()) {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
      String jsonText = readAll(rd);
      return new JSONObject(jsonText);
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    String protocol = "http";
    String host = "localhost";
    int port = 8082;

    String depAirportCode = "BUD";
    String arrAirportCode = "TSR";

    String url = protocol + "://" + host + ":" + port + "/flight?dep=" + depAirportCode + "&arr=" + arrAirportCode;

    JSONObject json = readJsonFromUrl(url);
    logger.info(json.toString());
    logger.info("restResponse.duration=" + json.get("duration"));
  }
}