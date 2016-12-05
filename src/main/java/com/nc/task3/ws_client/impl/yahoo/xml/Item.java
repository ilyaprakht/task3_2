package com.nc.task3.ws_client.impl.yahoo.xml;


import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;

public class Item {

    @JacksonXmlProperty(localName = "condition")
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }
}
