package com.nc.task3.controller.impl;

import com.nc.task3.dao.WeatherDAO;
import com.nc.task3.dao.WeatherDAOService;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;
import com.nc.task3.exception.WeatherClientException;
import com.nc.task3.ws_server.WeatherResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class RestWeatherControllerTest {

    private RestWeatherController restWeatherController;

    private static final String DEFAULT_CITY = "saratov";
    private static final String DEFAULT_TEMP = "30";
    private static final String DEFAULT_TEXT = "Cloudy";

    @Before
    public void init() {
        restWeatherController = new RestWeatherController();
    }

    @Test
    public void handlePositive() throws DAOException {
        CityWeather cityWeather = new CityWeather(DEFAULT_CITY, DEFAULT_TEMP, DEFAULT_TEXT);

        WeatherDAOService mockWeatherDAOService = Mockito.mock(WeatherDAOService.class);
        WeatherDAO mockWeatherDAO = Mockito.mock(WeatherDAO.class);
        Mockito.doReturn(cityWeather).when(mockWeatherDAO).getWeather(DEFAULT_CITY);
        Mockito.doReturn(mockWeatherDAO).when(mockWeatherDAOService).getWeatherDAO();

        restWeatherController.setWeatherDAOService(mockWeatherDAOService);

        WeatherResult result = (WeatherResult) restWeatherController.handle(DEFAULT_CITY);

        assertNotNull("result is null", result);
        assertEquals("result is incorrect", result.getResult(), WeatherResult.SUCCESS_RESULT);
        assertEquals("weather city is incorrect", result.getWeather().getCity(), DEFAULT_CITY);
        assertEquals("weather temp is incorrect", result.getWeather().getTemp(), DEFAULT_TEMP);
        assertEquals("weather text is incorrect", result.getWeather().getText(), DEFAULT_TEXT);
    }

    @Test
    public void handleNegative() throws DAOException {
        WeatherDAOService mockWeatherDAOService = Mockito.mock(WeatherDAOService.class);
        Mockito.doThrow(DAOException.class).when(mockWeatherDAOService).getWeatherDAO();

        restWeatherController.setWeatherDAOService(mockWeatherDAOService);

        WeatherResult result = (WeatherResult) restWeatherController.handle(DEFAULT_CITY);

        assertNotNull("result is null", result);
        assertNull("weather is incorrect", result.getWeather());
        assertEquals("result is incorrect", result.getResult(), WeatherResult.FAIL_RESULT);
    }

    @Test
    public void handleNotFound() throws DAOException {
        WeatherDAOService mockWeatherDAOService = Mockito.mock(WeatherDAOService.class);
        WeatherDAO mockWeatherDAO = Mockito.mock(WeatherDAO.class);
        Mockito.doReturn(null).when(mockWeatherDAO).getWeather(DEFAULT_CITY);
        Mockito.doReturn(mockWeatherDAO).when(mockWeatherDAOService).getWeatherDAO();

        restWeatherController.setWeatherDAOService(mockWeatherDAOService);

        WeatherResult result = (WeatherResult) restWeatherController.handle(DEFAULT_CITY);

        assertNotNull("result is null", result);
        assertNull("weather is incorrect", result.getWeather());
        assertEquals("result is incorrect", result.getResult(), WeatherResult.NOT_FOUND_RESULT);
    }

}
