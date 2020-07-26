package com.example.openweatherapp;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //private DataCity dataCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //checkBundle();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this,new String []
                {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    /**
        mMap = googleMap;
        String mLocation = new StringBuilder(dataCity.getNameCity()).append(dataCity.getCountryCity()).toString();
        double iLong = Double.parseDouble(dataCity.getLongCoordCity());
        double iLat = Double.parseDouble(dataCity.getLatCoordCity());
        // Add a marker in Sydney and move the camera
        LatLng cityPlace = new LatLng(iLat, iLong);
        mMap.addMarker(new MarkerOptions().position(cityPlace).title(mLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cityPlace));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(2.0f));
    */
    }

    private void checkBundle() {
    /**
        Bundle bundle = getIntent().getExtras();

        dataCity = null;

        if(bundle != null){

            dataCity = new DataCity();
            dataCity = bundle.getParcelable("city");
        }

    */
    }
}