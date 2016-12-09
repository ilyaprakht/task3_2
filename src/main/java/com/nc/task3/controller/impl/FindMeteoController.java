package com.nc.task3.controller.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.jms.JMSService;
import com.nc.task3.controller.Controller;
import com.nc.task3.controller.Result;
import com.nc.task3.ws_client.Weather;
import com.nc.task3.ws_client.WeatherService;


public class FindMeteoController implements Controller {

    private WeatherService weatherService;
    private JMSService jmsService;

    public Object handle(String city) {
        Weather weather = weatherService.getWeatherClient().findWeather(city);

        CityWeather cityWeather = new CityWeather(city, weather.getTemp(), weather.getText());

        jmsService.getJmsSender().sendMessage(cityWeather);

        return new Result(true, cityWeather.getCity() + "->" + cityWeather.getTemp() + "->" + cityWeather.getText()); // TODO temp
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public JMSService getJmsService() {
        return jmsService;
    }

    public void setJmsService(JMSService jmsService) {
        this.jmsService = jmsService;
    }
}
