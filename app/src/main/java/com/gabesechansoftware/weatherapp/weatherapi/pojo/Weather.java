package com.gabesechansoftware.weatherapp.weatherapi.pojo;

import java.util.Date;

public class Weather {
    long id;
    Date applicable_date;
    String weather_state_name;
    String weather_state_abbr;
    double wind_speed;
    double wind_direction;
    String wind_direction_compass;
    double min_temp, max_temp, the_temp;  //All in Celsius
    double air_pressure;  //in mbar
    double humidity;
    double visibility; //miles
    double predictability;

    public long getId() {
        return id;
    }

    public Date getApplicable_date() {
        return applicable_date;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_direction() {
        return wind_direction;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public double getMin_temp() {
        return min_temp;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public double getThe_temp() {
        return the_temp;
    }

    public double getAir_pressure() {
        return air_pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getPredictability() {
        return predictability;
    }
}
