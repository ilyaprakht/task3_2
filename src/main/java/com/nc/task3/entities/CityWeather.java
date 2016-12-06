package com.nc.task3.entities;

import java.io.Serializable;

public class CityWeather implements Serializable{

    private String city;
    private String temp;
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

}
