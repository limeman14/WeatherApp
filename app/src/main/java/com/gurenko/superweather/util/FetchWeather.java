package com.gurenko.superweather.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;

import com.gurenko.superweather.R;

public class FetchWeather {

    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    public static JSONObject getWeatherJSON(ForecastType type, Context context, String city){
        try {
            URL url;
            switch (type) {
                case DAILY: {
                    url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
                    break;
                }
                case FOUR_DAYS: {
                    url = new URL("unavailable.com");
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }

            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("x-api-key",
                    context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder json = new StringBuilder(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
