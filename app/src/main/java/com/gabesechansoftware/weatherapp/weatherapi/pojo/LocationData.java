package com.gabesechansoftware.weatherapp.weatherapi.pojo;

import java.util.Date;
import java.util.List;

public class LocationData {
    String title;
    LocationType location_type;
    LatLong latt_long;
    Date time;
    Date sun_rise, sun_set;
    String timezone_name;
    LocationDescription parent;
    List<Weather> consolidated_weather;

    public String getTitle() {
        return title;
    }

    public LocationType getLocationType() {
        return location_type;
    }

    public Double getLatitude() {
        return latt_long != null ? latt_long.latitude : null;
    }

    public Double getLongitude() {
        return latt_long != null ? latt_long.longitude: null;
    }

    public Date getTime() {
        return time;
    }

    public Date getSunrise() {
        return sun_rise;
    }

    public Date getSunset() {
        return sun_set;
    }

    public String getTimezoneName() {
        return timezone_name;
    }

    public LocationDescription getParent() {
        return parent;
    }

    public List<Weather> getWeathers() {
        return consolidated_weather;
    }
}
