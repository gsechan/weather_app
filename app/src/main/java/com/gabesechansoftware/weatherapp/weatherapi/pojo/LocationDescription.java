package com.gabesechansoftware.weatherapp.weatherapi.pojo;

public class LocationDescription {
    String title;
    LocationType location_type;
    LatLong latt_long;
    long woeid;
    int distance;

    public Double getLatitude() {
        return latt_long != null ? latt_long.latitude : null;
    }

    public Double getLongitude() {
        return latt_long != null ? latt_long.longitude: null;
    }

    public String getTitle() {
        return title;
    }

    public LocationType getLocation_type() {
        return location_type;
    }

    public long getWoeid() {
        return woeid;
    }

    public int getDistance() {
        return distance;
    }
}
