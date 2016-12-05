package com.nc.task3.jsp_controller.impl;

import com.nc.task3.jsp_controller.Controller;
import com.nc.task3.jsp_controller.Result;
import com.nc.task3.ws_client.Weather;
import com.nc.task3.ws_client.WeatherService;


public abstract class FindMeteoController implements Controller {

    protected WeatherService weatherService;

    public Result handle(String city) {
        initBeans();

        Weather weather = weatherService.getWeatherClient().findWeather(city);

        return new Result(true, weather.getTemp() + "->" + weather.getText()); // TODO temp
    }

    abstract protected void initBeans();
}
