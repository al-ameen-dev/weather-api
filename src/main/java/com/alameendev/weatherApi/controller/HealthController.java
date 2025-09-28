package com.alameendev.weatherApi.controller;


import com.alameendev.weatherApi.dtos.HealthStatusDTO;
import com.alameendev.weatherApi.service.HealthService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private final HealthService healthService;
    private final Logger logger = LogManager.getLogger(HealthController.class);

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @Operation(summary = "Api endpoint for retrieving health status of Application")
    @GetMapping("")
    public HealthStatusDTO getApiHealth() {
        logger.info("/health endpoint called!");
        return healthService.getHealthStatus();
    }
}
