package com.alameendev.weatherApi.dtos;

public class WeatherForLocationDTO {

    private String name;

    private String state;

    private String country;

    private CurrentWeatherUnits current_weather_units;
    private CurrentWeather current_weather;

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

    public CurrentWeatherUnits getCurrent_weather_units() {
        return current_weather_units;
    }

    public void setCurrent_weather_units(CurrentWeatherUnits current_weather_units) {
        this.current_weather_units = current_weather_units;
    }

    public CurrentWeather getCurrent_weather() {
        return current_weather;
    }

    public void setCurrent_weather(CurrentWeather current_weather) {
        this.current_weather = current_weather;
    }


    public static class CurrentWeatherUnits {

        private String time;
        private String interval;
        private String temperature;
        private String windspeed;
        private String winddirection;
        private String is_day;
        private String weathercode;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }

        public String getWinddirection() {
            return winddirection;
        }

        public void setWinddirection(String winddirection) {
            this.winddirection = winddirection;
        }

        public String getIs_day() {
            return is_day;
        }

        public void setIs_day(String is_day) {
            this.is_day = is_day;
        }

        public String getWeathercode() {
            return weathercode;
        }

        public void setWeathercode(String weathercode) {
            this.weathercode = weathercode;
        }
    }

    public static class CurrentWeather {

        private String time;
        private String interval;
        private float temperature;
        private float windspeed;
        private float winddirection;
        private Boolean is_day;
        private Integer weathercode;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public float getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(float windspeed) {
            this.windspeed = windspeed;
        }

        public float getWinddirection() {
            return winddirection;
        }

        public void setWinddirection(float winddirection) {
            this.winddirection = winddirection;
        }

        public Boolean getIs_day() {
            return is_day;
        }

        public void setIs_day(Boolean is_day) {
            this.is_day = is_day;
        }

        public Integer getWeathercode() {
            return weathercode;
        }

        public void setWeathercode(Integer weathercode) {
            this.weathercode = weathercode;
        }
    }
}
