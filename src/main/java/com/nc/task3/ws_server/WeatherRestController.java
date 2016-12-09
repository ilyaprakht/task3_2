package com.nc.task3.ws_server;

import com.nc.task3.controller.Controller;
import com.nc.task3.controller.ControllerService;
import com.nc.task3.utils.SpringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherRestController {

    @RequestMapping("/weather")
    public WeatherResult weather(@RequestParam(value="city") String city) {
        Controller controller = ((ControllerService) SpringUtils.getBean("controllerService")).getRestWeatherController();
        return (WeatherResult) controller.handle(city);
    }

}
