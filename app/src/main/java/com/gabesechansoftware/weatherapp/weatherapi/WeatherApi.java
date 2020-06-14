package com.gabesechansoftware.weatherapp.weatherapi;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LocationData;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LocationDescription;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface WeatherApi {

    @GET("/api/location/search/")
    Call<List<LocationDescription>> searchLocationByName(@Query("query") String query);

    @GET("/api/location/search/")
    Call<List<LocationDescription>> searchForPosition(@Query("lattlong") String lattlong);

    @GET("/api/location/{woeid}/")
    Call<LocationData> getLocationData(@Path("woeid") Long id);

    @GET("/api/location/{woeid}/{date}/")
    Call<List<Weather>> getWeatherForLocationAndDate(@Path("woeid") Long id, String date);

}