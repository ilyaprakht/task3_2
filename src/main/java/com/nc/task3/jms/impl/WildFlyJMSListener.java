package com.nc.task3.jms.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.jms.JMSListener;


public class WildFlyJMSListener implements JMSListener {

    public void handleMessage(CityWeather cityWeather) {
        System.out.println(cityWeather); //TODO right handler
    }

}
