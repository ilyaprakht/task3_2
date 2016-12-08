package com.nc.task3.jms.impl;

import com.nc.task3.dao.WeatherDAOService;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.jms.JMSListener;


public class WildFlyJMSListener implements JMSListener {

    private WeatherDAOService weatherDAOService;

    public void handleMessage(CityWeather cityWeather) {
        weatherDAOService.getWeatherDAO().saveWeather(cityWeather);
    }

    public WeatherDAOService getWeatherDAOService() {
        return weatherDAOService;
    }

    public void setWeatherDAOService(WeatherDAOService weatherDAOService) {
        this.weatherDAOService = weatherDAOService;
    }
}
