package com.example.myapplication20190802;

import android.app.Application;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Loc extends Application {

    ArrayList<LatLng> locations = new ArrayList<>();
    double Latitude;
    double Longitude;

    public Loc() {
    }

    public Loc(double latitude, double longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public ArrayList<LatLng> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<LatLng> locations) {
        this.locations = locations;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
