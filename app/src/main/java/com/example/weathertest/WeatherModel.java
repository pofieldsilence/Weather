package com.example.weathertest;

import java.util.ArrayList;

public class WeatherModel {
    String city;
    String mainDescription;
    String description;
    double temperature;
    double pressure;
    int humidity;
    double wind_speed;
    double press;
    String sunriseTime;
    String sunsetTime;
    ArrayList<DailyModel> dailyModels;

    WeatherModel(String city, double temperature, double pressure, int humidity, double wind_speed, double press, String sunriseTime, String sunsetTime, String mainDescription, String description, ArrayList<DailyModel> dailyModels) {
        this.city = city;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        this.mainDescription = mainDescription;
        this.description = description;
        this.wind_speed = wind_speed;
        this.press = press;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.dailyModels = dailyModels;

    }


}
