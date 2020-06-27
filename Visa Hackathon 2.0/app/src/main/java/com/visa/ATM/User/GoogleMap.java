package com.visa.ATM.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.visa.ATM.CashProvider.HomeCashProvider;
import com.visa.ATM.CashProviders;
import com.visa.ATM.R;
import com.visa.ATM.data;


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
        updateAllCashprovidersLocation(googleMap);

        googleMap.setOnInfoWindowClickListener(new com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getApplicationContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),RequestHelper.class);
                intent.putExtra("ATM_NAME",marker.getTitle());
                startActivity(intent);
            }
        });

//        googleMap.setOnMarkerClickListener(new com.google.android.gms.maps.GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                return false;
//            }
//        });

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

    public void updateAllCashprovidersLocation(final com.google.android.gms.maps.GoogleMap googleMap){
        DatabaseReference cashProviders = FirebaseDatabase.getInstance().getReference().child("cashProviders");
        cashProviders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot cashProvider : snapshot.getChildren())
                {
                    CashProviders cashp = cashProvider.getValue(CashProviders.class);
                    assert cashp != null;
//                    Log.d("Location",""+cashp.getLatitude() +  " " + cashp.getLongitude()  );
                    LatLng point = new LatLng(cashp.getLatitude(),cashp.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(point).title("VISA atm :" + cashp.getName()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
