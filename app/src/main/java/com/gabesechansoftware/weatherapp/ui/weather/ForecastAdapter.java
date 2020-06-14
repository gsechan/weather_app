package com.gabesechansoftware.weatherapp.ui.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabesechansoftware.weatherapp.R;
import com.gabesechansoftware.weatherapp.WeatherViewModel;
import com.gabesechansoftware.weatherapp.weatherapi.pojo.Weather;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<Weather> weathers;

    ForecastAdapter(WeatherViewModel weatherViewModel, LifecycleOwner lifecycleOwner) {
        weatherViewModel.getLocationData().observe(lifecycleOwner, locationData -> {
            if(locationData == null) {
                weathers = new ArrayList<>();
            }
            else {
                weathers = locationData.getWeathers();
            }
            notifyDataSetChanged();
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView temperature;
        TextView date;
        ImageView image;
        ViewHolder(View v) {
            super(v);
            temperature = v.findViewById(R.id.temperature);
            date = v.findViewById(R.id.date);
            image = v.findViewById(R.id.weather_image);
        }
    }

    @Override
    public @NonNull ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_weathers, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weather weather = weathers.get(position);
        holder.temperature.setText(
                holder.itemView.getContext().getString(
                        R.string.future_temp,
                        toDegreesF(weather.getMin_temp()),
                        toDegreesF(weather.getMax_temp())));
        String formattedDate = DateFormat.getDateInstance(DateFormat.SHORT).format(weather.getApplicable_date());
        holder.date.setText(formattedDate);
        String url = "https://www.metaweather.com/static/img/weather/png/" + weather.getWeather_state_abbr() + ".png";
        Picasso.get().load(url).resize(100, 100)
                .centerInside().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return weathers != null ? weathers.size() : 0;
    }

    private int toDegreesF(double temp) {
        return (int)Math.round((temp*9)/5+32);
    }

}
