package com.visa.ATM.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
        LatLng point = new LatLng( 19.169257,73.341601);
        googleMap.addMarker(new MarkerOptions().position(point).title("Sample VISA ATM"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
    }
}
