package com.flighttime.flighttimebackend;

public class Flight {

    private long depLat;
    private long depLong;
    private long arrLat;
    private long arrLong;
    private String depAeroport;
    private String arrAeroport;

    private long dist;

    public Flight(String depAeroport, String arrAeroport) {
        this.depAeroport = depAeroport;
        this.arrAeroport = arrAeroport;
    }

    public Flight(long depLat, long depLong, long arrLat, long arrLong, String depAeroport, String arrAeroport) {
        this.depLat = depLat;
        this.depLong = depLong;
        this.arrLat = arrLat;
        this.arrLong = arrLong;
        this.depAeroport = depAeroport;
        this.arrAeroport = arrAeroport;
    }

    public Flight(long id, String content) {
    }

    public long getDepLat() {
        return depLat;
    }

    public long getDepLong() {
        return depLong;
    }

    public long getArrLat() {
        return arrLat;
    }

    public long getArrLong() {
        return arrLong;
    }

    public String getDepAeroport() {
        return depAeroport;
    }

    public String getArrAeroport() {
        return arrAeroport;
    }
}