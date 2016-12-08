package com.nc.task3.dao;


import com.nc.task3.entities.CityWeather;

public interface WeatherDAO {

    void saveWeather(CityWeather cityWeather);

    CityWeather getWeather(String city);

}
