package com.example.weathertest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;

import java.util.List;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements Weather.WeatherCallback, MyLocationListener.LocationCallback {

    TextView city;
    TextView degree;
    TextView status;
    ImageView icon;
    AllianceLoader preloader;
    TextView text_wind;
    ImageView ic_wind;
    TextView text_pressure;
    ImageView ic_pressure;
    TextView text_humidity;
    ImageView ic_humidity;
    TextView text_sunset;
    ImageView ic_sunset;
    TextView text_sundown;
    ImageView ic_sundown;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getAccessToLocation();

    }

    void getAccessToLocation() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
    }

    void initView() {

        city = findViewById(R.id.city_field);
        degree = findViewById(R.id.degree);
        status = findViewById(R.id.status);
        icon = findViewById(R.id.icon);
        preloader = findViewById(R.id.preloader);
        text_wind = findViewById(R.id.text_wind);
        ic_wind = findViewById(R.id.ic_wind);
        text_humidity = findViewById(R.id.text_humidity);
        ic_humidity = findViewById(R.id.ic_humidity);
        text_pressure = findViewById(R.id.text_pressure);
        ic_pressure = findViewById(R.id.ic_pressure);
        text_sundown = findViewById(R.id.text_sundown);
        ic_sundown = findViewById(R.id.ic_sundown);
        text_sunset = findViewById(R.id.text_sunset);
        ic_sunset = findViewById(R.id.ic_sunset);
        recyclerView = findViewById(R.id.dailyRecycler);

    }

    void getLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getAccessToLocation();

            return;
        }
        MyLocationListener myLocationListener = new MyLocationListener(this);
        MyLocationListener myLocationListener2 = new MyLocationListener(this);
        List<String> providers = lm.getAllProviders();
        for (int i = 0; i < providers.size(); i++) {
            String provider = providers.get(i);
            if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
                lm.requestLocationUpdates(provider, 15000, 500, myLocationListener);

            } else if (provider.equals(LocationManager.GPS_PROVIDER)) {
                lm.requestLocationUpdates(provider, 15000, 500, myLocationListener2);

            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length != 0) {
            if (grantResults[0] == PERMISSION_GRANTED) {
                getLocation();

            } else
                Toast.makeText(this, "Не удалось определить местоположение :(", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGetWeather(final WeatherModel model) {
        runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                preloader.setVisibility(View.GONE);

                city.setText(model.city);
                degree.setText(model.temperature);

                text_wind.setText(model.wind_speed);
                ic_wind.setImageResource(R.drawable.ic_wind);

                text_humidity.setText(model.humidity);
                ic_humidity.setImageResource(R.drawable.ic_humidity);

                text_pressure.setText(model.pressure);
                ic_pressure.setImageResource(R.drawable.ic_pressure);

                text_sunset.setText(model.sunsetTime);
                ic_sunset.setImageResource(R.drawable.ic_sunset);

                text_sundown.setText(model.sunriseTime);
                ic_sundown.setImageResource(R.drawable.ic_sundown);

                icon.setImageResource(model.icon);

                status.setText(model.description);

                recyclerView.setAdapter(new DailyAdapter(model.dailyModels));

            }
        });
    }

    @Override
    public void onLocationFounded(Double latitude, Double longitude) {
        Weather.getWeather(this, latitude, longitude);
    }


}