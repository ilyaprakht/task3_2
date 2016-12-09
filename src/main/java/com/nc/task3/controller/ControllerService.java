package com.nc.task3.controller;


public class ControllerService {

    private Controller findMeteoController;
    private Controller restWeatherController;

    public Controller getFindMeteoController() {
        return findMeteoController;
    }

    public void setFindMeteoController(Controller findMeteoController) {
        this.findMeteoController = findMeteoController;
    }

    public Controller getRestWeatherController() {
        return restWeatherController;
    }

    public void setRestWeatherController(Controller restWeatherController) {
        this.restWeatherController = restWeatherController;
    }
}
