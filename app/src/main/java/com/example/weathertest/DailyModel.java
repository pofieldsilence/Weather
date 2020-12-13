package com.example.weathertest;

public class DailyModel {
    String date;
    String day;
    int icon;
    String tempDay;
    String tempNight;

    DailyModel(String date, String day, String mainDescriptionForIcon, double tempDay, double tempNight) {
        this.date = date;
        this.day = day.substring(0, 1).toUpperCase() + day.substring(1);
        switch (mainDescriptionForIcon) {
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
        if (tempDay > 0) this.tempDay = "+" + (int) tempDay + " °C";
        else this.tempDay = (int) tempDay + " °C";
        if (tempNight > 0) this.tempNight = "+" + (int) tempNight + " °C";
        else this.tempNight = (int) tempNight + " °C";

    }


}
