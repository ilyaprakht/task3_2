package com.nc.task3.jms.impl;

import com.nc.task3.dao.WeatherDAOService;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;
import com.nc.task3.exception.JMSListenException;
import com.nc.task3.jms.JMSListener;
import com.nc.task3.ws_client.impl.yahoo.YahooWeatherClient;
import org.apache.log4j.Logger;


public class WildFlyJMSListener implements JMSListener {

    private final static Logger LOG = Logger.getLogger(WildFlyJMSListener.class);

    private WeatherDAOService weatherDAOService;

    public void handleMessage(CityWeather cityWeather) throws JMSListenException {
        try {
            weatherDAOService.getWeatherDAO().saveWeather(cityWeather);
        } catch (DAOException e) {
            LOG.error(JMSListenException.DAO_MESSAGE + ": cityWeather=" + cityWeather, e);
            throw new JMSListenException(JMSListenException.DAO_MESSAGE);
        } catch (Exception e) {
            LOG.error(JMSListenException.UNKNOWN_MESSAGE + ": cityWeather=" + cityWeather, e);
            throw new JMSListenException(JMSListenException.UNKNOWN_MESSAGE);
        }
    }

    public void setWeatherDAOService(WeatherDAOService weatherDAOService) {
        this.weatherDAOService = weatherDAOService;
    }
}
