package com.flighttime.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Airport {

    private int id;
    private String ident;
    private String type;
    private String name;
    private double latitude_deg;
    private double longitude_deg;
    private int elevation_ft;
    private String continent;
    private String iso_country;
    private String iso_region;
    private String municipality;
    private boolean scheduled_service;
    private String gps_code;
    private String iata_code;
    private String local_code;
    private String home_link;
    private String wikipedia_link;
    private String keywords;
    private String score;
    private LocalDateTime last_updated;

  public Airport() {

  }

  public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude_deg() {
        return latitude_deg;
    }

    public void setLatitude_deg(double latitude_deg) {
        this.latitude_deg = latitude_deg;
    }

    public double getLongitude_deg() {
        return longitude_deg;
    }

    public void setLongitude_deg(double longitude_deg) {
        this.longitude_deg = longitude_deg;
    }

    public int getElevation_ft() {
        return elevation_ft;
    }

    public void setElevation_ft(int elevation_ft) {
        this.elevation_ft = elevation_ft;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getIso_country() {
        return iso_country;
    }

    public void setIso_country(String iso_country) {
        this.iso_country = iso_country;
    }

    public String getIso_region() {
        return iso_region;
    }

    public void setIso_region(String iso_region) {
        this.iso_region = iso_region;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public boolean isScheduled_service() {
        return scheduled_service;
    }

    public void setScheduled_service(boolean scheduled_service) {
        this.scheduled_service = scheduled_service;
    }

    public String getGps_code() {
        return gps_code;
    }

    public void setGps_code(String gps_code) {
        this.gps_code = gps_code;
    }

    public String getIata_code() {
        return iata_code;
    }

    public void setIata_code(String iata_code) {
        this.iata_code = iata_code;
    }

    public String getLocal_code() {
        return local_code;
    }

    public void setLocal_code(String local_code) {
        this.local_code = local_code;
    }

    public String getHome_link() {
        return home_link;
    }

    public void setHome_link(String home_link) {
        this.home_link = home_link;
    }

    public String getWikipedia_link() {
        return wikipedia_link;
    }

    public void setWikipedia_link(String wikipedia_link) {
        this.wikipedia_link = wikipedia_link;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(LocalDateTime last_updated) {
        this.last_updated = last_updated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return Double.compare(airport.getLatitude_deg(), getLatitude_deg()) == 0 &&
                Double.compare(airport.getLongitude_deg(), getLongitude_deg()) == 0 &&
                Objects.equals(getName(), airport.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLatitude_deg(), getLongitude_deg());
    }

    public Airport(int id, String ident, String type, String name, double latitude_deg, double longitude_deg, int elevation_ft, String continent, String iso_country, String iso_region, String municipality, boolean scheduled_service, String gps_code, String iata_code, String local_code, String home_link, String wikipedia_link, String keywords, String score, LocalDateTime last_updated) {
        this.id = id;
        this.ident = ident;
        this.type = type;
        this.name = name;
        this.latitude_deg = latitude_deg;
        this.longitude_deg = longitude_deg;
        this.elevation_ft = elevation_ft;
        this.continent = continent;
        this.iso_country = iso_country;
        this.iso_region = iso_region;
        this.municipality = municipality;
        this.scheduled_service = scheduled_service;
        this.gps_code = gps_code;
        this.iata_code = iata_code;
        this.local_code = local_code;
        this.home_link = home_link;
        this.wikipedia_link = wikipedia_link;
        this.keywords = keywords;
        this.score = score;
        this.last_updated = last_updated;
    }

}