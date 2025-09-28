package com.alameendev.weatherApi.controller;


import com.alameendev.weatherApi.dtos.WeatherForLocationDTO;
import com.alameendev.weatherApi.dtos.WeatherForecastDTO;
import com.alameendev.weatherApi.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final Logger logger = LogManager.getLogger(WeatherController.class);

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @Operation(summary = "Api endpoint for retrieving weather information by City Name")
    @Cacheable(cacheNames = "current", key = "#cityName")
    @GetMapping("/current")
    public ResponseEntity<List<WeatherForLocationDTO>> getCurrentWeatherByLocation(@RequestParam("location") String cityName) {
        logger.info("Weather controller /current end point called!");
        return ResponseEntity.ok(weatherService.getWeatherForTheLocation(cityName));
    }

    @Operation(summary = "Api endpoint for retrieving weather forecast for specified days by City Name")
    @Cacheable(cacheNames = "forecast")
    @GetMapping("/forecast")
    public ResponseEntity<WeatherForecastDTO> getForecastOfCityForNDays(@RequestParam("location") String cityName, @RequestParam(value = "days", defaultValue = "5") Integer noOfDays) {
        logger.info("Weather controller /forecast end point called!");
        return ResponseEntity.ok(weatherService.getWeatherForecastForCityWithDays(cityName, noOfDays));
    }


}
