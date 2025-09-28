package com.alameendev.weatherApi.dtos;

public class HealthStatusDTO {

    private String status;
    private String uptime;

    public HealthStatusDTO(String status, String uptime) {
        this.status = status;
        this.uptime = uptime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }
}
