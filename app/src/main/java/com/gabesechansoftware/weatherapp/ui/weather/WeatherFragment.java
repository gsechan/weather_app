package com.gabesechansoftware.weatherapp.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabesechansoftware.weatherapp.R;
import com.gabesechansoftware.weatherapp.WeatherViewModel;

public class WeatherFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WeatherViewModel weatherViewModel =
                ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);
        View root = inflater.inflate(R.layout.fragment_weather, container, false);
        setupRecyclerView(root, weatherViewModel);
        weatherViewModel.getLocationData().observe(getViewLifecycleOwner(), locationData ->  {
            TextView title = root.findViewById(R.id.title);
            TextView temperature = root.findViewById(R.id.temperature);
            if(locationData != null) {
                title.setText(locationData.getTitle());
                temperature.setText(toDegreesF(locationData.getWeathers().get(0).getThe_temp())+" F");
            }
            else {
                title.setText(R.string.today_weather);
                temperature.setText("");
            }
        });

        return root;
    }

    private void setupRecyclerView(View root, WeatherViewModel weatherViewModel) {
        RecyclerView recyclerView = root.findViewById(R.id.forecast_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        ForecastAdapter locationAdapter = new ForecastAdapter(weatherViewModel, this);
        recyclerView.setAdapter(locationAdapter);

    }


    private int toDegreesF(double temp) {
        return (int)Math.round((temp*9)/5+32);
    }
}
