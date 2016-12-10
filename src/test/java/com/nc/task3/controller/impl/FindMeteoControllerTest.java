package com.nc.task3.controller.impl;

import com.nc.task3.controller.SimpleResult;
import com.nc.task3.exception.WeatherClientException;
import com.nc.task3.jms.JMSSender;
import com.nc.task3.jms.JMSService;
import com.nc.task3.ws_client.Weather;
import com.nc.task3.ws_client.WeatherClient;
import com.nc.task3.ws_client.WeatherClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class FindMeteoControllerTest {

    private FindMeteoController findMeteoController;

    private static final String DEFAULT_CITY = "saratov";
    private static final String DEFAULT_TEMP = "30";
    private static final String DEFAULT_TEXT = "Cloudy";

    @Before
    public void init() {
        findMeteoController = new FindMeteoController();
    }

    @Test
    public void handlePositive() throws WeatherClientException {
        WeatherClientService mockWeatherClientService = Mockito.mock(WeatherClientService.class);
        WeatherClient mockWeatherClient = Mockito.mock(WeatherClient.class);
        Weather mockWeather = Mockito.mock(Weather.class);
        Mockito.doReturn(DEFAULT_TEMP).when(mockWeather).getTemp();
        Mockito.doReturn(DEFAULT_TEXT).when(mockWeather).getText();
        Mockito.doReturn(mockWeather).when(mockWeatherClient).findWeather(DEFAULT_CITY);
        Mockito.doReturn(mockWeatherClient).when(mockWeatherClientService).getWeatherClient();

        JMSService mockJmsService = Mockito.mock(JMSService.class);
        JMSSender mockJmsSender = Mockito.mock(JMSSender.class);
        Mockito.doReturn(mockJmsSender).when(mockJmsService).getJmsSender();

        findMeteoController.setWeatherClientService(mockWeatherClientService);
        findMeteoController.setJmsService(mockJmsService);

        SimpleResult result = (SimpleResult) findMeteoController.handle(DEFAULT_CITY);

        assertNotNull("result is null", result);
        assertTrue("result is incorrect", result.getResult());
    }

    @Test
    public void handleNegative() {
        WeatherClientService mockWeatherClientService = Mockito.mock(WeatherClientService.class);
        Mockito.doThrow(WeatherClientException.class).when(mockWeatherClientService).getWeatherClient();

        findMeteoController.setWeatherClientService(mockWeatherClientService);

        SimpleResult result = (SimpleResult) findMeteoController.handle(DEFAULT_CITY);

        assertNotNull("result is null", result);
        assertFalse("result is incorrect", result.getResult());
    }
}
