package com.nc.task3.jms;


import com.nc.task3.entities.CityWeather;

public interface JMSSender {

    void sendMessage(CityWeather cityWeather);

}
