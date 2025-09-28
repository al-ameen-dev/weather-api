package com.alameendev.weatherApi.exceptions;

public class InvalidCityNameException extends IllegalArgumentException {

    public InvalidCityNameException(String cityName) {
        super("The city with the name '" + cityName + "' doesn't exist! please enter valid name!");
    }
}
