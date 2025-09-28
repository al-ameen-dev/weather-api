package com.alameendev.weatherApi.dtos;

public class LatitudeLongitudeDTO {

    private String name;

    private String country;

    private String state;
    private String lat;
    private String lon;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }


}

