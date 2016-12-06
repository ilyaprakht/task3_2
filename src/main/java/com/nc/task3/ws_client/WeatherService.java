package com.nc.task3.ws_client;


public class WeatherService {

    private WeatherClient weatherClient;

    public WeatherClient getWeatherClient() {
        return weatherClient;
    }

    public void setWeatherClient(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }
}
