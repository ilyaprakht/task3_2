package com.nc.task3.controller.impl;

import com.nc.task3.controller.Controller;
import com.nc.task3.dao.WeatherDAOService;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;
import com.nc.task3.ws_server.WeatherResult;


public class RestWeatherController implements Controller {

    private WeatherDAOService weatherDAOService;

    public Object handle(String city) {
        WeatherResult result = null;
        try {
            CityWeather cityWeather = weatherDAOService.getWeatherDAO().getWeather(city);
            String stringResult = (cityWeather == null ? WeatherResult.NOT_FOUND_RESULT : WeatherResult.SUCCESS_RESULT);
            result = new WeatherResult(stringResult, cityWeather);
        } catch (DAOException e) {
            result = new WeatherResult(WeatherResult.FAIL_RESULT);
        }
        return result;
    }

    public WeatherDAOService getWeatherDAOService() {
        return weatherDAOService;
    }

    public void setWeatherDAOService(WeatherDAOService weatherDAOService) {
        this.weatherDAOService = weatherDAOService;
    }
}
