package com.visa.ATM.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.visa.ATM.R;


public class GoogleMap extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        setCurrentLocation(googleMap);


    }

    public void setCurrentLocation(final com.google.android.gms.maps.GoogleMap googleMap){
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    android.location.Location location = task.getResult();
                    if(location!=null){
//                        Toast.makeText(this,location.getLatitude()+"", Toast.LENGTH_SHORT).show();
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        LatLng point = new LatLng(latitude, longitude);
                        googleMap.addMarker(new MarkerOptions().position(point).title("me"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));

                    }
                }
            });
        }else{
            ActivityCompat.requestPermissions((Activity)this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
    }
}
