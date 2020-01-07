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

  public Flight(AirportV2 depAirport, AirportV2 arrAeroport) {
    this.depAeroport = depAirport.getCode();
    this.arrAeroport = arrAeroport.getCode();

    convertAndSet(depAirport.getLat(), depAirport.getLon(), arrAeroport.getLat(), arrAeroport.getLon(),
        depAirport.getElev(), arrAeroport.getElev());

    this.dist =
        distance(depLat, depLon, arrLat, arrLat, depElev, arrElev);

    this.duration = calculateDuration(this.dist);

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

  public double getDuration() {
    return duration;
  }

  public void setDuration(double duration) {
    this.duration = duration;
  }

  private void convertAndSet(String depLatString, String depLonString, String arrLatString, String arrLotString,
                             String depElevString, String arrElevString) {
    this.depLat = Double.valueOf(depLatString);
    this.depLon = Double.valueOf(depLonString);
    this.arrLat = Double.valueOf(arrLotString);
    this.arrLat = Double.valueOf(arrLatString);
    this.depElev = Double.valueOf(depElevString);
    this.arrElev = Double.valueOf(arrElevString);

  }

  /**
   * result in minutes
   * "30 min plus 1 hour per every 500 miles"
   * src: https://openflights.org/faq
   *
   * @param dist
   * @return
   */
  private double calculateDuration(double dist) {
    return 30 + 60 * dist;
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
  public static double distance(double lat1, double lat2, double lon1,
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
}