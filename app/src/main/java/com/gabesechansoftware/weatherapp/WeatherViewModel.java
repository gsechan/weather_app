package com.gabesechansoftware.weatherapp;

import com.gabesechansoftware.weatherapp.weatherapi.WeatherController;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LocationData;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LocationDescription;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {

    String lastQuery;
    Double lastLatitude, lastLongitude;

    private MutableLiveData<List<LocationDescription>> locations;
    private MutableLiveData<LocationDescription> locationChosen;
    private MutableLiveData<LocationData> locationData;
    private WeatherController weatherController;

    public WeatherViewModel() {
        locations = new MutableLiveData<>();
        locationChosen = new MutableLiveData<>();
        locationData = new MutableLiveData<>();
        weatherController = new WeatherController();
    }

    public LiveData<List<LocationDescription>> getLocations() {
        return locations;
    }

    public MutableLiveData<LocationDescription> getLocationChosen() {
        return locationChosen;
    }

    public MutableLiveData<LocationData> getLocationData() {
        return locationData;
    }

    public void searchForLocation(String query) {
        //Only perform a search if the query string changed, otherwise we're doing extra requests
        if(lastQuery != null && lastQuery.equals(query)) {
            return;
        }
        lastLatitude = null;
        lastLongitude = null;
        lastQuery = query;
        locations.setValue(null);
        locationChosen.setValue(null);
        weatherController.searchForLocation(query, locations);
    }

    public void searchForLocation(double latitude, double longitude) {
        //Only perform a search if the query string changed, otherwise we're doing extra requests
        if(lastLatitude != null && lastLatitude.equals(latitude) && lastLongitude.equals(longitude)) {
            return;
        }
        lastLatitude = latitude;
        lastLongitude = longitude;
        lastQuery = null;
        locations.setValue(null);
        locationChosen.setValue(null);
        weatherController.searchForPosition(latitude, longitude, locations);
    }

    public void setLocationChosen(int index) {
        setLocationChosen(locations.getValue().get(index));
    }

    public void setLocationChosen(LocationDescription location) {
        if(location == null) {
            locationChosen.setValue(location);
            locationData.setValue(null);
            return;
        }
        //Only perform a request if this is a new choice
        if(location.equals(locationChosen.getValue())) {
            return;
        }
        locationChosen.setValue(location);
        locationData.setValue(null);
        weatherController.getLocationData(location, locationData);
    }

}


