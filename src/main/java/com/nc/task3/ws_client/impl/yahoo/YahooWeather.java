package com.nc.task3.ws_client.impl.yahoo;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;
import com.nc.task3.ws_client.Weather;
import com.nc.task3.ws_client.impl.yahoo.xml.Results;


class YahooWeather implements Weather {

    @JacksonXmlProperty(isAttribute = true)
    private String count;

    @JacksonXmlProperty(isAttribute = true)
    private String created;

    @JacksonXmlProperty(isAttribute = true)
    private String lang;

    @JacksonXmlProperty(localName = "results")
    private Results results;

    public String getTemp() {
        return results.getChannel().getItem().getCondition().getTemp();
    }

    public String getText() {
        return results.getChannel().getItem().getCondition().getText();
    }
}


