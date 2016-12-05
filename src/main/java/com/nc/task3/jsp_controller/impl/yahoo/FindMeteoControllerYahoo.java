package com.nc.task3.jsp_controller.impl.yahoo;


import com.nc.task3.jsp_controller.impl.FindMeteoController;
import com.nc.task3.ws_client.impl.yahoo.YahooWeatherService;

public class FindMeteoControllerYahoo extends FindMeteoController {

    protected void initBeans() {
        weatherService = new YahooWeatherService(); //TODO get from context
    }

}
