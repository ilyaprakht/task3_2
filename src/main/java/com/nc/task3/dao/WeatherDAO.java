package com.nc.task3.dao;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;


public interface WeatherDAO {

    void saveWeather(CityWeather cityWeather) throws DAOException;

    CityWeather getWeather(String city) throws DAOException;

}
