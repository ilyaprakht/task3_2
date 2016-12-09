package com.nc.task3.controller.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.JMSSendException;
import com.nc.task3.exception.WeatherClientException;
import com.nc.task3.jms.JMSService;
import com.nc.task3.controller.Controller;
import com.nc.task3.controller.SimpleResult;
import com.nc.task3.ws_client.Weather;
import com.nc.task3.ws_client.WeatherClientService;


public class FindMeteoController implements Controller {

    private static final String UNKNOWN_ERROR_MESSAGE = "Unknown error";

    private WeatherClientService weatherClientService;
    private JMSService jmsService;

    public Object handle(String city) {
        SimpleResult result = null;
        try {
            Weather weather = weatherClientService.getWeatherClient().findWeather(city);
            CityWeather cityWeather = new CityWeather(city, weather.getTemp(), weather.getText());
            jmsService.getJmsSender().sendMessage(cityWeather);
            result = new SimpleResult(true);
        } catch (WeatherClientException e) {
            result = new SimpleResult(false, e.getMessage());
        } catch (JMSSendException e) {
            result = new SimpleResult(false, e.getMessage());
        } catch (Exception e) {
            result = new SimpleResult(false, UNKNOWN_ERROR_MESSAGE);
        }
        return result;
    }

    public void setWeatherClientService(WeatherClientService weatherClientService) {
        this.weatherClientService = weatherClientService;
    }

    public void setJmsService(JMSService jmsService) {
        this.jmsService = jmsService;
    }
}
