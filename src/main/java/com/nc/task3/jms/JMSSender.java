package com.nc.task3.jms;


import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.JMSSendException;

public interface JMSSender {

    void sendMessage(CityWeather cityWeather) throws JMSSendException;

}
