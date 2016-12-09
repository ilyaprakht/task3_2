package com.nc.task3.jms;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.JMSListenException;
import org.springframework.transaction.annotation.Transactional;


public interface JMSListener {

    @Transactional
    void handleMessage(CityWeather cityWeather) throws JMSListenException;

}
