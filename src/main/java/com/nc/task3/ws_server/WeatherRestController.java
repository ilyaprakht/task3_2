package com.nc.task3.ws_server;

import com.nc.task3.controller.Controller;
import com.nc.task3.controller.ControllerService;
import com.nc.task3.utils.SpringUtils;
import com.nc.task3.ws_client.impl.yahoo.YahooWeatherClient;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherRestController {

    private final static Logger LOG = Logger.getLogger(WeatherRestController.class);

    @RequestMapping("/weather")
    public WeatherResult weather(@RequestParam(value="city") String city) {
        LOG.debug("city=" + city);
        Controller controller = ((ControllerService) SpringUtils.getBean("controllerService")).getRestWeatherController();
        return (WeatherResult) controller.handle(city);
    }

}
