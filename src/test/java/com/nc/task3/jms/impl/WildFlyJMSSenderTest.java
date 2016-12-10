package com.nc.task3.jms.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.JMSSendException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jms.InvalidDestinationException;
import org.springframework.jms.core.JmsTemplate;


public class WildFlyJMSSenderTest {

    private WildFlyJMSSender wildFlyJMSSender;
    private CityWeather cityWeather;

    private static final String DEFAULT_CITY = "saratov";
    private static final String DEFAULT_TEMP = "30";
    private static final String DEFAULT_TEXT = "Cloudy";

    @Before
    public void init() {
        wildFlyJMSSender = new WildFlyJMSSender();
        cityWeather = new CityWeather(DEFAULT_CITY, DEFAULT_TEMP, DEFAULT_TEXT);
    }

    @Test
    public void sendMessagePositive() throws JMSSendException {
        JmsTemplate mockJmsTemplate = Mockito.mock(JmsTemplate.class);
        wildFlyJMSSender.setJmsTemplate(mockJmsTemplate);

        wildFlyJMSSender.sendMessage(cityWeather);
    }

    @Test(expected = JMSSendException.class)
    public void sendMessageNegative() throws JMSSendException {
        JmsTemplate mockJmsTemplate = Mockito.mock(JmsTemplate.class);
        Mockito.doThrow(InvalidDestinationException.class).when(mockJmsTemplate).convertAndSend(cityWeather);
        wildFlyJMSSender.setJmsTemplate(mockJmsTemplate);

        wildFlyJMSSender.sendMessage(cityWeather);
    }

}
