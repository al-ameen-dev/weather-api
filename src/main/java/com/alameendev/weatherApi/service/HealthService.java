package com.alameendev.weatherApi.service;

import com.alameendev.weatherApi.dtos.HealthStatusDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    private long startTimeMillis;

    @PostConstruct
    public void init() {
        this.startTimeMillis = System.currentTimeMillis();
    }

    public long getUptimeInMillis() {
        return System.currentTimeMillis() - startTimeMillis;
    }

    public HealthStatusDTO getHealthStatus(){
        long uptimeMillis = getUptimeInMillis();
        long seconds = (uptimeMillis / 1000) % 60;
        long minutes = (uptimeMillis / 1000 / 60) % 60;
        long hours = (uptimeMillis / 1000 / 60 / 60);
        String upTime = String.format("%d:%02d:%02d",hours,minutes,seconds);
        return new HealthStatusDTO("UP",upTime);
    }
}
