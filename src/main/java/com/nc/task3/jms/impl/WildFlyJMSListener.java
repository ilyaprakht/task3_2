package com.nc.task3.jms.impl;

import com.nc.task3.dao.WeatherDAOService;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;
import com.nc.task3.exception.JMSListenException;
import com.nc.task3.jms.JMSListener;


public class WildFlyJMSListener implements JMSListener {

    private WeatherDAOService weatherDAOService;

    public void handleMessage(CityWeather cityWeather) throws JMSListenException {
        try {
            weatherDAOService.getWeatherDAO().saveWeather(cityWeather);
        } catch (DAOException e) {
            // TODO log
            throw new JMSListenException(JMSListenException.DAO_MESSAGE);
        } catch (Exception e) {
            // TODO log
            throw new JMSListenException(JMSListenException.UNKNOWN_MESSAGE);
        }
    }

    public void setWeatherDAOService(WeatherDAOService weatherDAOService) {
        this.weatherDAOService = weatherDAOService;
    }
}
