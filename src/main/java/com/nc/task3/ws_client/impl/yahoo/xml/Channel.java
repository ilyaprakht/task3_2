package com.nc.task3.ws_client.impl.yahoo.xml;


import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;

public class Channel {

    @JacksonXmlProperty(localName = "item")
    private Item item;

    public Item getItem() {
        return item;
    }
}
