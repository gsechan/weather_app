package com.gabesechansoftware.weatherapp.ui.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabesechansoftware.weatherapp.R;
import com.gabesechansoftware.weatherapp.WeatherViewModel;

public class LocationFragment extends Fragment {

    private WeatherViewModel weatherViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        weatherViewModel =
                ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);
        View root = inflater.inflate(R.layout.fragment_location, container, false);

        setupRecyclerView(root);
        setupSearchView(root);
        weatherViewModel.getLocations().observe(getViewLifecycleOwner(), locations-> {
            //If only 1 location exists, lets select it
            if(locations != null && locations.size() > 0) {
                weatherViewModel.setLocationChosen(0);
            }
            else {
                weatherViewModel.setLocationChosen(null);
            }
        });
        return root;
    }

    private void setupSearchView(View root) {
        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                weatherViewModel.searchForLocation(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void setupRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.location_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LocationDescriptionAdapter locationAdapter = new LocationDescriptionAdapter(weatherViewModel, this);
        recyclerView.setAdapter(locationAdapter);

    }
}
