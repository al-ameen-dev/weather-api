package com.alameendev.weatherApi.controller;

import com.alameendev.weatherApi.dtos.WeatherForLocationDTO;
import com.alameendev.weatherApi.service.LocationService;
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
@RequestMapping("/locations")

public class LocationController {
    private final LocationService locationService;
    private final Logger logger = LogManager.getLogger(LocationController.class);

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Api endpoint for the retrieving Weather information for the locations based on 'fuzzy search' ")
    @Cacheable(cacheNames = "search", key = "#query")
    @GetMapping("/search")
    public ResponseEntity<List<WeatherForLocationDTO>> getCurrentWeatherByLocation(@RequestParam("q") String query) {
        logger.info("Location controllers search endpoint called!");
        return ResponseEntity.ok(locationService.getWeatherInfoForLocations(query));
    }

}
