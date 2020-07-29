package com.example.weathertest;

import java.util.ArrayList;

public class WeatherModel {
    String city;
    int icon;
    String description;
    String temperature;
    String pressure;
    String humidity;

    String wind_speed;
    String sunriseTime;
    String sunsetTime;
    ArrayList<DailyModel> dailyModels;


    WeatherModel(String city, double temperature, double pressure, int humidity, double wind_speed, String sunriseTime, String sunsetTime, String mainDescription, String description, ArrayList<DailyModel> dailyModels) {
        this.city = city;
        this.humidity = humidity + " %";
        this.pressure = (int) pressure + " мм.рт.ст.";

        if (temperature > 0)
            this.temperature = "+" + (int) temperature + " °C";
        else this.temperature = (int) temperature + " °C";

        switch (mainDescription) {
            case "Thunderstorm":
                icon = (R.drawable.ic_thunderstorm);
                break;
            case "Drizzle":
                icon = (R.drawable.ic_drizzle);
                break;
            case "Rain":
                icon = (R.drawable.ic_rain);
                break;
            case "Snow":
                icon = (R.drawable.ic_snow);
                break;
            case "Atmosphere":
                icon = (R.drawable.ic_atmosphere);
                break;
            case "Clear":
                icon = (R.drawable.ic_clear);
                break;
            case "Clouds":
                icon = (R.drawable.ic_clouds);
                break;
        }
        this.description = description;

        this.wind_speed = wind_speed + " м/с";
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.dailyModels = dailyModels;


    }

    public void run() {

    }


}
