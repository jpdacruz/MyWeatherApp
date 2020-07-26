package com.example.openweatherapp.ui;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.openweatherapp.R;
import com.example.openweatherapp.model.entity.CityEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CityEntity cityEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        checkBundle();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this,new String []
                {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        String mLocation = new StringBuilder(cityEntity.getNameCity()).append(cityEntity.getCountryCity()).toString();
        double iLong = Double.parseDouble(cityEntity.getLongCoordCity());
        double iLat = Double.parseDouble(cityEntity.getLatCoordCity());
        // Add a marker in Sydney and move the camera
        LatLng cityPlace = new LatLng(iLat, iLong);
        mMap.addMarker(new MarkerOptions().position(cityPlace).title(mLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cityPlace));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(2.0f));

    }

    private void checkBundle() {

        Bundle bundle = getIntent().getExtras();

        cityEntity = null;

        if(bundle != null){

            cityEntity = new CityEntity();
            cityEntity = bundle.getParcelable("city");
        }
    }
}