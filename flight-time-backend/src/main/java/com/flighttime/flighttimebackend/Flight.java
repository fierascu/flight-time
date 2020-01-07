package com.flighttime.flighttimebackend;

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

    convertAndSet(depAirport.getLat(), depAirport.getLon(), arrAeroport.getLat(), arrAeroport.getLon(),
        depAirport.getElev(), arrAeroport.getElev());

    this.dist = Utils.calculateDistanceFromPoints(depLat, arrLat, depLon, arrLon, depElev, arrElev);

    this.duration = Utils.calculateDurationFromMeters(dist);

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

  private void convertAndSet(String depLatString, String depLonString, String arrLatString, String arrLotString,
                             String depElevString, String arrElevString) {
    this.depLat = Double.parseDouble(depLatString);
    this.depLon = Double.parseDouble(depLonString);
    this.arrLat = Double.parseDouble(arrLotString);
    this.arrLon = Double.parseDouble(arrLatString);
    this.depElev = Double.parseDouble(depElevString);
    this.arrElev = Double.parseDouble(arrElevString);

  }

}