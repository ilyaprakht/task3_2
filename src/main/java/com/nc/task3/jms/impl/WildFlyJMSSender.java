package com.nc.task3.jms.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.jms.JMSSender;
import org.springframework.jms.core.JmsTemplate;


public class WildFlyJMSSender implements JMSSender {

    private JmsTemplate jmsTemplate;

    public void sendMessage(CityWeather cityWeather) {
        jmsTemplate.convertAndSend(cityWeather);
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
