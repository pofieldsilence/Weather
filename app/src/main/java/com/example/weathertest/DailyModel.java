package com.example.weathertest;

public class DailyModel {
    String date;
    String day;
    String mainDescriptionForIcon;
    Double tempDay;
    Double tempNight;

    DailyModel(String date, String day, String mainDescriptionForIcon, Double tempDay, Double tempNight){
        this.date = date;
        this.day = day;
        this.mainDescriptionForIcon = mainDescriptionForIcon;
        this.tempDay = tempDay;
        this.tempNight = tempNight;
    }


}
