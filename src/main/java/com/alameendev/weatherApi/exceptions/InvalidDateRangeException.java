package com.alameendev.weatherApi.exceptions;

public class InvalidDateRangeException extends IllegalArgumentException {

    public InvalidDateRangeException() {
        super("Invalid number of days. Please choose between 1 and 16.");
    }

}
