package com.gabesechansoftware.weatherapp.ui.location;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabesechansoftware.weatherapp.R;
import com.gabesechansoftware.weatherapp.WeatherViewModel;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.LocationDescription;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class LocationDescriptionAdapter extends RecyclerView.Adapter<LocationDescriptionAdapter.ViewHolder> {
    private static final int NONE_CHOSEN = -1;

    private List<LocationDescription> locations;
    private int chosen = NONE_CHOSEN;
    private WeatherViewModel weatherViewModel;

    LocationDescriptionAdapter(WeatherViewModel weatherViewModel, LifecycleOwner lifecycleOwner) {
        this.weatherViewModel = weatherViewModel;
        weatherViewModel.getLocations().observe(lifecycleOwner, descriptions -> {
            locations = descriptions;
            if(locations != null && locations.size() > 0) {
                weatherViewModel.setLocationChosen(0);
                chosen = 0;
            }
            notifyDataSetChanged();
        });
        weatherViewModel.getLocationChosen().observe(lifecycleOwner, choice-> {
            if(chosen != NONE_CHOSEN) {
                notifyItemChanged(chosen);
            }
            if(locations != null) {
                chosen = locations.indexOf(choice);
                notifyItemChanged(chosen);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        TextView locationPosition;
        ViewHolder(View v) {
            super(v);
            locationName = v.findViewById(R.id.location_name);
            locationPosition = v.findViewById(R.id.lat_long);
        }
    }

    @Override
    public @NonNull LocationDescriptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_location_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocationDescription description = locations.get(position);
        holder.locationName.setText(description.getTitle());
        holder.locationPosition.setText(description.getLatitude()+", "+description.getLongitude());

        if(chosen == position) {
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorAccent, null));
        }
        else {
            holder.itemView.setBackgroundColor(0);
        }
        holder.itemView.setOnClickListener(v -> weatherViewModel.setLocationChosen(position));
    }

    @Override
    public int getItemCount() {
        return locations != null ? locations.size() : 0;
    }
}
