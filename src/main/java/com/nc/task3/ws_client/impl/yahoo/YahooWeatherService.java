package com.nc.task3.ws_client.impl.yahoo;

import com.nc.task3.ws_client.WeatherClient;
import com.nc.task3.ws_client.WeatherService;


public class YahooWeatherService implements WeatherService {

    private WeatherClient weatherClient;

    public WeatherClient getWeatherClient() {
        if (weatherClient == null) {
            weatherClient = new YahooWeatherClient();
        }
        return weatherClient;
    }
}
