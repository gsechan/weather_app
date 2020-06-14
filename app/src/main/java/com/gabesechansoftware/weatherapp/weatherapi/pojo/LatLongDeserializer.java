package com.gabesechansoftware.weatherapp.weatherapi.pojo;

import android.text.TextUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class LatLongDeserializer implements JsonDeserializer<LatLong> {
    public LatLong deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String value = json.getAsString();
        if(TextUtils.isEmpty(value)) {
            return null;
        }
        int indexComma = value.indexOf(",");
        if(indexComma == -1) {
            throw new JsonParseException("Invalid latt/long- no comma");
        }
        try {
            double latitude = Double.parseDouble(value.substring(0, indexComma));
            double longitude = Double.parseDouble(value.substring(indexComma + 1));
            return new LatLong(latitude, longitude);
        }
        catch(NumberFormatException|NullPointerException ex) {
            throw new JsonParseException(ex);
        }
    }
}