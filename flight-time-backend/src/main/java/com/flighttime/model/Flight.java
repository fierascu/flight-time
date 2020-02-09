package com.flighttime.model;

import com.flighttime.app.Utils;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import static com.flighttime.app.Utils.formatDouble;

public class Flight {

    private double depLat;

    private double depLon;

    private double arrLat;

    private double arrLon;

    private String depAiroport;

    private String arrAiroport;

    private double dist;

    private double duration;

    private String durationAsTime;

    public Flight(Airport depAirport, Airport arrAirport) {
        if (depAirport == null || arrAirport == null) {
            return;
        }

        this.depAiroport = depAirport.getIata_code();
        this.arrAiroport = arrAirport.getIata_code();
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

    public String getDepAiroport() {
        return depAiroport;
    }

    public String getArrAiroport() {
        return arrAiroport;
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
                "depLat=" + depLat +
                ", depLon=" + depLon +
                ", arrLat=" + arrLat +
                ", arrLon=" + arrLon +
                ", depAeroport='" + depAiroport + '\'' +
                ", arrAeroport='" + arrAiroport + '\'' +
                ", dist=" + dist +
                ", duration=" + duration +
                ", durationAsTime='" + durationAsTime + '\'' +
                '}';
    }

}