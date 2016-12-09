package com.nc.task3.jms.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.JMSSendException;
import com.nc.task3.jms.JMSSender;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;


public class WildFlyJMSSender implements JMSSender {

    private JmsTemplate jmsTemplate;

    public void sendMessage(CityWeather cityWeather) throws JMSSendException {
        try {
            jmsTemplate.convertAndSend(cityWeather);
        } catch (JmsException e) {
            // TODO log
            throw new JMSSendException(JMSSendException.SEND_JMS_IN_QUEUE_MESSAGE);
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
