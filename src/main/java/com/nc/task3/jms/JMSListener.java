package com.nc.task3.jms;

import com.nc.task3.entities.CityWeather;


public interface JMSListener {

    void handleMessage(CityWeather cityWeather);

}
