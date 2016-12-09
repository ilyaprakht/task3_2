package com.nc.task3.ws_server;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;
import com.nc.task3.entities.CityWeather;


public class WeatherResult {

    public static final String SUCCESS_RESULT = "success";
    public static final String FAIL_RESULT = "fail";
    public static final String NOT_FOUND_RESULT = "not found";

    @JacksonXmlProperty(localName = "result")
    private String result;

    @JacksonXmlProperty(localName = "weather")
    private CityWeather weather;

    public WeatherResult(String result) {
        this.result = result;
    }

    public WeatherResult(String result, CityWeather cityWeather) {
        this(result);
        this.weather = cityWeather;
    }

    public String getSuccessResult() {
        return result;
    }

    public CityWeather getCityWeather() {
        return weather;
    }
}
