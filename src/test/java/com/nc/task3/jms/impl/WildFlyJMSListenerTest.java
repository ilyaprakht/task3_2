package com.nc.task3.jms.impl;

import com.nc.task3.dao.WeatherDAO;
import com.nc.task3.dao.WeatherDAOService;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;
import com.nc.task3.exception.JMSListenException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class WildFlyJMSListenerTest {

    private WildFlyJMSListener wildFlyJMSListener;
    private CityWeather cityWeather;

    private static final String DEFAULT_CITY = "saratov";
    private static final String DEFAULT_TEMP = "30";
    private static final String DEFAULT_TEXT = "Cloudy";

    @Before
    public void init() {
        wildFlyJMSListener = new WildFlyJMSListener();
        cityWeather = new CityWeather(DEFAULT_CITY, DEFAULT_TEMP, DEFAULT_TEXT);
    }

    @Test
    public void handleMessagePositive() throws JMSListenException {
        WeatherDAOService mockWeatherDAOService = Mockito.mock(WeatherDAOService.class);
        WeatherDAO mockWeatherDAO = Mockito.mock(WeatherDAO.class);
        Mockito.doReturn(mockWeatherDAO).when(mockWeatherDAOService).getWeatherDAO();
        wildFlyJMSListener.setWeatherDAOService(mockWeatherDAOService);

        wildFlyJMSListener.handleMessage(cityWeather);
    }

    @Test(expected = JMSListenException.class)
    public void handleMessageNegative() throws JMSListenException {
        WeatherDAOService mockWeatherDAOService = Mockito.mock(WeatherDAOService.class);
        Mockito.doThrow(DAOException.class).when(mockWeatherDAOService).getWeatherDAO();
        wildFlyJMSListener.setWeatherDAOService(mockWeatherDAOService);

        wildFlyJMSListener.handleMessage(cityWeather);
    }

}
