package com.nc.task3.controller.impl;


import com.nc.task3.controller.Controller;
import com.nc.task3.dao.WeatherDAOService;

public class RestWeatherController implements Controller {

    private WeatherDAOService weatherDAOService;

    public Object handle(String city) {
        return weatherDAOService.getWeatherDAO().getWeather(city);
    }

    public WeatherDAOService getWeatherDAOService() {
        return weatherDAOService;
    }

    public void setWeatherDAOService(WeatherDAOService weatherDAOService) {
        this.weatherDAOService = weatherDAOService;
    }
}
