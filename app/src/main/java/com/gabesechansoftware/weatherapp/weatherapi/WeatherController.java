package com.gabesechansoftware.weatherapp.weatherapi;

import com.gabesechansoftware.weatherapp.weatherapi.pojo.LatLong;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LatLongDeserializer;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LocationData;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LocationDescription;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherController {
    static final String BASE_URL = "https://www.metaweather.com/";
    final WeatherApi weatherApi;

    public WeatherController() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(LatLong.class, new LatLongDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        weatherApi = retrofit.create(WeatherApi.class);

    }

    private class MLDCallback<T> implements Callback<T> {
        MutableLiveData<T> mutableLiveData;

        MLDCallback(MutableLiveData<T> mutableLiveData) {
            this.mutableLiveData = mutableLiveData;
        }

        @Override
        public void onResponse(Call<T> call,
                               Response<T> response) {
            if (response.isSuccessful()){
                mutableLiveData.setValue(response.body());
            }
            else {
                mutableLiveData.setValue(null);
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            mutableLiveData.setValue(null);
            t.printStackTrace();
        }
    }

    public void searchForLocation(String query, MutableLiveData<List<LocationDescription>> mld){
        weatherApi.searchLocationByName(query).enqueue(new MLDCallback<>(mld));
    }

    public void searchForPosition(double latitude, double longitude, MutableLiveData<List<LocationDescription>> mld){
        weatherApi.searchForPosition(latitude+","+longitude).enqueue(new MLDCallback<>(mld));
    }

    public void getLocationData(LocationDescription description, MutableLiveData<LocationData> mld){
        weatherApi.getLocationData(description.getWoeid()).enqueue(new MLDCallback<>(mld));
    }


}
