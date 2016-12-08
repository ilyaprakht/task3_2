package com.nc.task3.jms;

import com.nc.task3.entities.CityWeather;
import org.springframework.transaction.annotation.Transactional;


public interface JMSListener {

    @Transactional
    void handleMessage(CityWeather cityWeather);

}
