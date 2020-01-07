package com.flighttime.flighttimebackend;

import java.util.Objects;

public class Airport {

  private String id;

  private String icao;

  private String iata;

  private String name;

  private String city;

  private String state;

  private String country;

  private String elevation;

  private long lat;

  private long lon;

  private String tz;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIcao() {
    return icao;
  }

  public void setIcao(String icao) {
    this.icao = icao;
  }

  public String getIata() {
    return iata;
  }

  public void setIata(String iata) {
    this.iata = iata;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getElevation() {
    return elevation;
  }

  public void setElevation(String elevation) {
    this.elevation = elevation;
  }

  public long getLat() {
    return lat;
  }

  public void setLat(long lat) {
    this.lat = lat;
  }

  public long getLon() {
    return lon;
  }

  public void setLon(long lon) {
    this.lon = lon;
  }

  public String getTz() {
    return tz;
  }

  public void setTz(String tz) {
    this.tz = tz;
  }

  public Airport(String id, String icao, String iata, String name, String city, String state, String country,
                 String elevation, long lat, long lon, String tz) {
    this.id = id;
    this.icao = icao;
    this.iata = iata;
    this.name = name;
    this.city = city;
    this.state = state;
    this.country = country;
    this.elevation = elevation;
    this.lat = lat;
    this.lon = lon;
    this.tz = tz;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (!(o instanceof Airport)) {
          return false;
      }
    Airport airport = (Airport)o;
    return getId().equals(airport.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

}