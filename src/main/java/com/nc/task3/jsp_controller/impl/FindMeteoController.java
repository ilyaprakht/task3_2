package com.nc.task3.jsp_controller.impl;

import com.nc.task3.jsp_controller.Controller;
import com.nc.task3.jsp_controller.Result;
import com.nc.task3.spring.SpringUtils;
import com.nc.task3.ws_client.Weather;
import com.nc.task3.ws_client.WeatherService;


public class FindMeteoController implements Controller {

    public Result handle(String city) {
        WeatherService weatherService = (WeatherService) SpringUtils.getBean("WeatherService");
        Weather weather = weatherService.getWeatherClient().findWeather(city);

        return new Result(true, weather.getTemp() + "->" + weather.getText()); // TODO temp
    }

}
