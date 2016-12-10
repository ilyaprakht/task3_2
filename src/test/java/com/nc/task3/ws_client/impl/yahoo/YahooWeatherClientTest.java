package com.nc.task3.ws_client.impl.yahoo;

import com.nc.task3.exception.WeatherClientException;
import com.nc.task3.ws_client.Weather;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class YahooWeatherClientTest {

    private YahooWeatherClient yahooWeatherClient;
    private Map<String, String> variablesMap;

    private static final String DEFAULT_CITY = "saratov";
    private static final String YAHOO_URL = "https://query.yahooapis.com/v1/public/yql?q=select item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"{city}\")&format=xml";
    private static final String DEFAULT_XML_STRING = "<query xmlns:yahoo=\"http://www.yahooapis.com/v1/base.rng\" yahoo:count=\"1\" yahoo:created=\"2016-12-10T18:54:13Z\" yahoo:lang=\"ru-RU\">\n" +
            "<results><channel><item>\n" +
            "<yweather:condition xmlns:yweather=\"http://xml.weather.yahoo.com/ns/rss/1.0\" code=\"27\" date=\"Sat, 10 Dec 2016 09:00 PM MSK\" temp=\"31\" text=\"Mostly Cloudy\"/>\n" +
            "</item></channel></results></query>";

    @Before
    public void init() {
        yahooWeatherClient = new YahooWeatherClient();
        variablesMap = new HashMap<String, String>();
        variablesMap.put("city", DEFAULT_CITY);
    }

    @Test
    public void findWeatherPositive() throws WeatherClientException {
        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        Mockito.doReturn(DEFAULT_XML_STRING).when(mockRestTemplate).getForObject(YAHOO_URL, String.class, variablesMap);

        yahooWeatherClient.setRestTemplate(mockRestTemplate);

        Weather weather = yahooWeatherClient.findWeather(DEFAULT_CITY);

        assertNotNull("weather is null", weather);
        assertNotNull("weather temp is null", weather.getTemp());
        assertNotNull("weather text is null", weather.getText());
    }

    @Test(expected = WeatherClientException.class)
    public void findWeatherNegative() throws WeatherClientException {
        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        Mockito.doThrow(WeatherClientException.class).when(mockRestTemplate).getForObject(YAHOO_URL, String.class, variablesMap);

        yahooWeatherClient.setRestTemplate(mockRestTemplate);

        yahooWeatherClient.findWeather(DEFAULT_CITY);
    }

}
