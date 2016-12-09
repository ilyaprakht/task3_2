package com.nc.task3.entities;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;

import java.io.Serializable;

public class CityWeather implements Serializable{

    @JacksonXmlProperty(localName = "city")
    private String city;

    @JacksonXmlProperty(localName = "temp")
    private String temp;

    @JacksonXmlProperty(localName = "text")
    private String text;

    public CityWeather(String city, String temp, String text) {
        this.city = city;
        this.temp = temp;
        this.text = text;
    }

    public String getCity() {
        return city;
    }

    public String getTemp() {
        return temp;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return "Weather in city " + city + ": temp=" + temp + ", text=" + text;
    }

}
