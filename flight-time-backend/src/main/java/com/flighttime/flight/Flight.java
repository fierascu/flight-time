package com.flighttime.flight;

import static com.flighttime.flight.Utils.formatDouble;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Flight {

  private double depLat;

  private double depLon;

  private double arrLat;

  private double arrLon;

  private double depElev;

  private double arrElev;

  private String depAeroport;

  private String arrAeroport;

  private double dist;

  private double duration;

  private String durationAsTime;

  public Flight(AirportV2 depAirport, AirportV2 arrAeroport) {
    this.depAeroport = depAirport.getCode();
    this.arrAeroport = arrAeroport.getCode();
    this.depLat = Double.parseDouble(depAirport.getLat());
    this.depLon = Double.parseDouble(depAirport.getLon());
    this.arrLat = Double.parseDouble(arrAeroport.getLat());
    this.arrLon = Double.parseDouble(arrAeroport.getLon());

    LatLng depLng = new LatLng(depLat, depLon);
    LatLng arrLng = new LatLng(arrLat, arrLon);

    this.dist = formatDouble(LatLngTool.distance(depLng, arrLng, LengthUnit.NAUTICAL_MILE));

    this.duration = formatDouble(Utils.calculateDurationFromMiles(dist));

    this.durationAsTime = Utils.getDurationAsTime(duration);
  }

  public double getDepLat() {
    return depLat;
  }

  public double getDepLon() {
    return depLon;
  }

  public double getArrLat() {
    return arrLat;
  }

  public double getArrLon() {
    return arrLon;
  }

  public String getDepAeroport() {
    return depAeroport;
  }

  public String getArrAeroport() {
    return arrAeroport;
  }

  public double getDist() {
    return dist;
  }

  public void setDist(double dist) {
    this.dist = dist;
  }

  public double getDuration() {
    return duration;
  }

  public void setDuration(double duration) {
    this.duration = duration;
  }

  public String getDurationAsTime() {
    return durationAsTime;
  }

  public void setDurationAsTime(String durationAsTime) {
    this.durationAsTime = durationAsTime;
  }

}