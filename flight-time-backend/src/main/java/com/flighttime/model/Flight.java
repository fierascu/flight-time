package com.flighttime.model;

import com.flighttime.app.Utils;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import static com.flighttime.app.Utils.formatDouble;

public class Flight {

    private Airport depAirport;

    private Airport arrAirport;

    private double depLat;

    private double depLon;

    private double arrLat;

    private double arrLon;

    private String depAirportIataCode;

    private String arrAirportIataCode;

    private double dist;

    private double duration;

    private String durationAsTime;

    public Flight(Airport depAirport, Airport arrAirport) {
        if (depAirport == null || arrAirport == null) {
            return;
        }
        this.depAirport = depAirport;
        this.arrAirport = arrAirport;

        this.depAirportIataCode = depAirport.getIata_code();
        this.arrAirportIataCode = arrAirport.getIata_code();
        this.depLat = depAirport.getLatitude_deg();
        this.depLon = depAirport.getLongitude_deg();
        this.arrLat = arrAirport.getLatitude_deg();
        this.arrLon = arrAirport.getLongitude_deg();

        LatLng depLng = new LatLng(depLat, depLon);
        LatLng arrLng = new LatLng(arrLat, arrLon);

        this.dist = formatDouble(LatLngTool.distance(depLng, arrLng, LengthUnit.NAUTICAL_MILE));

        this.duration = formatDouble(Utils.calculateDurationFromMiles(dist));

        this.durationAsTime = Utils.getDurationAsTime(duration);
    }

    public Airport getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(Airport depAirport) {
        this.depAirport = depAirport;
    }

    public Airport getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(Airport arrAirport) {
        this.arrAirport = arrAirport;
    }

    public double getDepLat() {
        return depLat;
    }

    public void setDepLat(double depLat) {
        this.depLat = depLat;
    }

    public double getDepLon() {
        return depLon;
    }

    public void setDepLon(double depLon) {
        this.depLon = depLon;
    }

    public double getArrLat() {
        return arrLat;
    }

    public void setArrLat(double arrLat) {
        this.arrLat = arrLat;
    }

    public double getArrLon() {
        return arrLon;
    }

    public void setArrLon(double arrLon) {
        this.arrLon = arrLon;
    }

    public String getDepAirportIataCode() {
        return depAirportIataCode;
    }

    public void setDepAirportIataCode(String depAirportIataCode) {
        this.depAirportIataCode = depAirportIataCode;
    }

    public String getArrAirportIataCode() {
        return arrAirportIataCode;
    }

    public void setArrAirportIataCode(String arrAirportIataCode) {
        this.arrAirportIataCode = arrAirportIataCode;
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

    @Override
    public String toString() {
        return "Flight{" +
                "depAirport=" + depAirport +
                ", arrAirport=" + arrAirport +
                ", depLat=" + depLat +
                ", depLon=" + depLon +
                ", arrLat=" + arrLat +
                ", arrLon=" + arrLon +
                ", depAirportIataCode='" + depAirportIataCode + '\'' +
                ", arrAirportIataCode='" + arrAirportIataCode + '\'' +
                ", dist=" + dist +
                ", duration=" + duration +
                ", durationAsTime='" + durationAsTime + '\'' +
                '}';
    }
}