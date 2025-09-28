package com.alameendev.weatherApi.dtos;

import java.util.List;

public class WeatherForecastDTO {

    private String name;
    private String state;
    private String country;
    private DailyUnits daily_units;
    private Daily daily;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DailyUnits getDaily_units() {
        return daily_units;
    }

    public void setDaily_units(DailyUnits daily_units) {
        this.daily_units = daily_units;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public static class DailyUnits {
        private String time;
        private String temperature_2m_max;
        private String temperature_2m_min;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTemperature_2m_max() {
            return temperature_2m_max;
        }

        public void setTemperature_2m_max(String temperature_2m_max) {
            this.temperature_2m_max = temperature_2m_max;
        }

        public String getTemperature_2m_min() {
            return temperature_2m_min;
        }

        public void setTemperature_2m_min(String temperature_2m_min) {
            this.temperature_2m_min = temperature_2m_min;
        }
    }

    public static class Daily {

        private List<String> time;

        private List<Float> temperature_2m_max;
        private List<Float> temperature_2m_min;

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Float> getTemperature_2m_max() {
            return temperature_2m_max;
        }

        public void setTemperature_2m_max(List<Float> temperature_2m_max) {
            this.temperature_2m_max = temperature_2m_max;
        }

        public List<Float> getTemperature_2m_min() {
            return temperature_2m_min;
        }

        public void setTemperature_2m_min(List<Float> temperature_2m_min) {
            this.temperature_2m_min = temperature_2m_min;
        }
    }

}
