package com.nc.task3.ws_client;

import com.nc.task3.exception.WeatherClientException;

public interface WeatherClient {

    Weather findWeather(String city) throws WeatherClientException;

}
