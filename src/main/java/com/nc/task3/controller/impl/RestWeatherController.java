package com.nc.task3.controller.impl;

import com.nc.task3.controller.Controller;
import com.nc.task3.dao.WeatherDAOService;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;
import com.nc.task3.ws_client.impl.yahoo.YahooWeatherClient;
import com.nc.task3.ws_server.WeatherResult;
import org.apache.log4j.Logger;


public class RestWeatherController implements Controller {

    private final static Logger LOG = Logger.getLogger(RestWeatherController.class);

    private WeatherDAOService weatherDAOService;

    public Object handle(String city) {
        LOG.debug("city=" + city);
        WeatherResult result = null;
        try {
            CityWeather cityWeather = weatherDAOService.getWeatherDAO().getWeather(city);
            String stringResult = (cityWeather == null ? WeatherResult.NOT_FOUND_RESULT : WeatherResult.SUCCESS_RESULT);
            LOG.debug("stringResult=" + stringResult);
            result = new WeatherResult(stringResult, cityWeather);
        } catch (DAOException e) {
            result = new WeatherResult(WeatherResult.FAIL_RESULT);
        } catch (Exception e) {
            result = new WeatherResult(WeatherResult.FAIL_RESULT);
        }
        LOG.debug("result=" + result);
        return result;
    }

    public void setWeatherDAOService(WeatherDAOService weatherDAOService) {
        this.weatherDAOService = weatherDAOService;
    }
}
