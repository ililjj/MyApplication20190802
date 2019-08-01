package com.example.myapplication20190802;

import android.app.FragmentManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class Result extends AppCompatActivity implements OnMapReadyCallback {

    //달린시간 표시 텍스트뷰
    TextView textView_RunningTime;
    //달린거리 표시 텍스트뷰
    TextView textview_RunningDistance;

    Loc loc;
    ArrayList<LatLng> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView_RunningTime = findViewById(R.id.Textview_RunningTime);
        textview_RunningDistance = findViewById(R.id.Textview_RunningDistance);

        loc = (Loc)getApplication();
        locations = new ArrayList<>();
        locations.addAll(loc.getLocations());

        LatLng start = locations.get(0);
        LatLng end = locations.get(locations.size()-1);

        Location a = new Location("a");
        a.setLatitude(start.latitude);
        a.setLongitude(start.longitude);
        Location b = new Location("b");
        b.setLatitude(end.latitude);
        b.setLongitude(end.longitude);

        float distance = a.distanceTo(b);

        String num = String.format("%.3f" , distance);
        textview_RunningDistance.setText(num);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap map) {

        LatLng latLng = new LatLng(37.56, 126.97);

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(30);
        ArrayList<LatLng> Path = new ArrayList();
        Log.d("MAPLOGG","locations.size() = " + locations.size() );
        for(int i=0 ; i< locations.size() ;i++){
            latLng = locations.get(i);
            Log.d("MAPLOGG","locations.longitude() = " + latLng.longitude);
            Log.d("MAPLOGG","locations.latitude() = " + latLng.latitude );
            Path.add(latLng);
        }

        polylineOptions.addAll(Path);
        map.addPolyline(polylineOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,19);
        map.moveCamera(cameraUpdate);
      //  map.animateCamera(CameraUpdateFactory.zoomTo(19));

    }

    public double Calc (double lat1,double long1,double lat2,double long2){

        double Distance = 0;

        double dLat1InRad = lat1 * (Math.PI/180.0);
        double dLong1InRad = long1 * (Math.PI/180.0);
        double dLat2InRad = lat2 * (Math.PI/180.0);
        double dLong2InRad = long2 * (Math.PI/180.0);

        double dLongitude = dLong2InRad-dLong1InRad;
        double dLatitude = dLat2InRad-dLat1InRad;

        double a = Math.pow(Math.sin(dLatitude/2.0),2.0) + Math.cos(dLat1InRad) * Math.cos(dLat2InRad) * Math.pow(Math.sin(dLongitude/2.0),2.0);
        double c = 2.0 * Math.atan2(Math.sqrt(a),Math.sqrt(1.0-a));
        double kEarth = 6376.5;

        Distance = kEarth*c;

        return  Distance;
    }
}
