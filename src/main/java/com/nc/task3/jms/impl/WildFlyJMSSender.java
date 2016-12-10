package com.nc.task3.jms.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.JMSSendException;
import com.nc.task3.jms.JMSSender;
import com.nc.task3.ws_client.impl.yahoo.YahooWeatherClient;
import org.apache.log4j.Logger;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;


public class WildFlyJMSSender implements JMSSender {

    private final static Logger LOG = Logger.getLogger(WildFlyJMSSender.class);

    private JmsTemplate jmsTemplate;

    public void sendMessage(CityWeather cityWeather) throws JMSSendException {
        try {
            jmsTemplate.convertAndSend(cityWeather);
        } catch (JmsException e) {
            LOG.error(JMSSendException.SEND_JMS_IN_QUEUE_MESSAGE + ": cityWeather=" + cityWeather, e);
            throw new JMSSendException(JMSSendException.SEND_JMS_IN_QUEUE_MESSAGE);
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
