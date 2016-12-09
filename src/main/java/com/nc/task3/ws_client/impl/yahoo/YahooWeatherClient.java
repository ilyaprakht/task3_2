package com.nc.task3.ws_client.impl.yahoo;

import com.fasterxml.jackson.xml.XmlMapper;
import com.nc.task3.exception.WeatherClientException;
import com.nc.task3.ws_client.WeatherClient;
import com.nc.task3.ws_client.Weather;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class YahooWeatherClient implements WeatherClient {

    private static final String YAHOO_URL = "https://query.yahooapis.com/v1/public/yql?q=select item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"{city}\")&format=xml";

    public Weather findWeather(String city) throws WeatherClientException {
        Map<String, String> variablesMap = new HashMap<String, String>();
        variablesMap.put("city", city);

        RestTemplate restTemplate = new RestTemplate();
        String xmlString = null;
        try {
            xmlString = restTemplate.getForObject(YAHOO_URL, String.class, variablesMap);
        } catch (RestClientException e) {
            // TODO log
            throw new WeatherClientException(WeatherClientException.READ_MESSAGE);
        }

        ObjectMapper xmlMapper = new XmlMapper();
        YahooWeather weather = null;
        try {
            weather = xmlMapper.readValue(xmlString, YahooWeather.class);
        } catch (IOException e) {
            // TODO log
            throw new WeatherClientException(WeatherClientException.PARSE_MESSAGE);
        }

        return weather;
    }
}
