package com.example.weathertest;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;

public class MyLocationListener implements LocationListener {
    boolean locationIsFounded = false;
    LocationCallback callback;

    MyLocationListener(LocationCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d("MyLocationListener", String.valueOf(location.getLatitude()));

        if (!locationIsFounded) {
            locationIsFounded = true;
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            callback.onLocationFounded(latitude, longitude);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }


    @Override
    public void onProviderDisabled(@NonNull String provider) {
        callback.onLocationFail();
    }

    interface LocationCallback {
        void onLocationFounded(Double latitude, Double longitude);
        void onLocationFail();
    }
}