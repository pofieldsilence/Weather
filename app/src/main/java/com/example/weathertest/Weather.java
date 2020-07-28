package com.example.weathertest;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Weather {
    static final String API_KEY = "53ae43e26c671e28677f2f296df470c1";
    static final String query = "https://api.openweathermap.org/data/2.5/onecall?";
    static final String queryCity = "http://api.openweathermap.org/data/2.5/weather?";


//"lat={lat}&lon={lon}&appid={your api key}";

    static void getWeather(final WeatherCallback callback, final Double latitude, final Double longitude, final Context context) {
        final String request = getQuery(query, latitude, longitude);
        final String requestCity = getQuery(queryCity, latitude, longitude);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //get json
                    StringBuilder result = new StringBuilder();
                    HttpURLConnection connection = (HttpURLConnection) new URL(request).openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line).append('\n');
                    }
                    Log.d("getWeather", result.toString());
                    bufferedReader.close();
                    connection.disconnect();
                    //parse json

                    JSONObject json = new JSONObject(result.toString());

                    JSONObject current = json.getJSONObject("current");
                    double temp = current.getDouble("temp");
                    double tempr = temp - 273.15;
                    Log.d("getWeather", "Температура = " + tempr);

                    double pressure = current.getDouble("pressure");
                    double press = pressure / 1.333;
                    Log.d("getWeather", "Давление = " + press);

                    int humidity = current.getInt("humidity");
                    Log.d("getWeather", "Влажность = " + humidity);

                    JSONArray weatherRai = current.getJSONArray("weather");
                    JSONObject object1 = weatherRai.getJSONObject(0);

                    String mainDescription = object1.getString("main");

                    String description = object1.getString("description");

                    double wind_speed = current.getDouble("wind_speed");
                    Log.d("getWeather", "Скорость = " + wind_speed);

                    Calendar calendar = Calendar.getInstance();
                    TimeZone timeZone = calendar.getTimeZone();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                    simpleDateFormat.setTimeZone(timeZone);

                    Long sunrise = current.getLong("sunrise");
                    String sunriseTime = simpleDateFormat.format(new Date(sunrise * 1000 + 3 * 60 * 60 * 1000));
                    Log.d("getWeather", "Рассвет в " + sunriseTime);

                    Long sunset = current.getLong("sunset");
                    String sunsetTime = simpleDateFormat.format(new Date(sunset * 1000 + 3 * 60 * 60 * 1000));
                    Log.d("getWeather", "Закат в " + sunsetTime);

                    JSONArray daily = json.getJSONArray("daily");
                    ArrayList<DailyModel> listDaily = new ArrayList<>();
                    for (int i = 0; i < daily.length(); i++) {
                        JSONObject dailyObject = daily.getJSONObject(i);

                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("d LLLL", new Locale("ru"));
                        simpleDateFormat.setTimeZone(timeZone);
                        Long time = dailyObject.getLong("dt");
                        String dailyDate = simpleDateFormat1.format(new Date(time * 1000 + 3 * 60 * 60 * 1000));
                        Log.d("getWeather", "Месяц " + dailyDate);

                        simpleDateFormat1 = new SimpleDateFormat("cccc", new Locale("ru"));
                        String dayOfWeek = simpleDateFormat1.format(new Date(time * 1000 + 3 * 60 * 60 * 1000));
                        Log.d("getWeather", "День недели " + dayOfWeek);

                        JSONArray weather = dailyObject.getJSONArray("weather");
                        String status = weather.getJSONObject(0).getString("main");
                        Log.d("getWeather", "Status  " + status);

                        JSONObject tempDaily = dailyObject.getJSONObject("temp");

                        Double tempDay = Math.ceil(tempDaily.getDouble("day") - 273.15);
                        Log.d("getWeather", "TempDay  = " + tempDay);

                        Double tempNight = Math.floor(tempDaily.getDouble("night") - 273.15);
                        Log.d("getWeather", "TempNight  = " + tempNight);

                        DailyModel dailyModel = new DailyModel(dailyDate, dayOfWeek, status, tempDay, tempNight);
                        listDaily.add(dailyModel);

                    }

                    result = new StringBuilder();
                    connection = (HttpURLConnection) new URL(requestCity).openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line).append('\n');
                    }
                    Log.d("getWeather", result.toString());
                    bufferedReader.close();
                    connection.disconnect();
                    json = new JSONObject(result.toString());

                    String city = json.getString("name");


                    WeatherModel weather = new WeatherModel(city, tempr, press, humidity, wind_speed, press, sunriseTime, sunsetTime, mainDescription, description, listDaily);
                    callback.onGetWeather(weather);



                } catch (Exception e) {
                    Log.e("getWeather", e.getMessage());
                }

            }
        });
        thread.start();
    }

    static String getQuery(String query, Double latitude, Double longitude) {
        return query + "lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY + "&lang=ru";
    }


    interface WeatherCallback {
        void onGetWeather(WeatherModel model);
    }
}
