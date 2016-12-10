package com.nc.task3.ws_client.impl.yahoo.xml;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;


public class Condition {

    @JacksonXmlProperty(isAttribute = true)
    private String code;

    @JacksonXmlProperty(isAttribute = true)
    private String date;

    @JacksonXmlProperty(isAttribute = true)
    private String temp;

    @JacksonXmlProperty(isAttribute = true)
    private String text;

    public String getTemp() {
        return temp;
    }

    public String getText() {
        return text;
    }
}
