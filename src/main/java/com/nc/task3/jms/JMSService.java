package com.nc.task3.jms;


public class JMSService {

    private JMSSender jmsSender;
    private JMSListener jmsListener;

    public JMSSender getJmsSender() {
        return jmsSender;
    }

    public void setJmsSender(JMSSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    public JMSListener getJmsListener() {
        return jmsListener;
    }

    public void setJmsListener(JMSListener jmsListener) {
        this.jmsListener = jmsListener;
    }
}
