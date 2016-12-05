package com.nc.task3.ws_client.impl.yahoo.xml;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;


public class Results {

    @JacksonXmlProperty(localName = "channel")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }
}
