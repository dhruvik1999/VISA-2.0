package com.visa.ATM.CashProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.visa.ATM.R;
import com.visa.ATM.Request;
import com.visa.ATM.data;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Objects;


public class HomeCashProvider extends  AppCompatActivity {
    FetchCordinates fetchCordinates=new FetchCordinates();
    private ArrayList<Request> requestsList = new ArrayList<>();

    Intent locatorService = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cash_provider);
        //hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Dexter.withActivity(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                    Toast.makeText(HomeCashProvider.this,"You Have The Location Permission !",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(HomeCashProvider.this,"You Should Accept This Permission !!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
        RecyclerView recycler = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        final MyAdapter listAdapter = new MyAdapter(requestsList, this);
        recycler.setAdapter(listAdapter);
        recycler.setAdapter(listAdapter);

        //Load the date from the network or other resources
        //into the array list asynchronously
        String username=data.userId;
        FirebaseDatabase.getInstance().getReference().child("cashProviders").child(username).child("Requests").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        requestsList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Request request1 =snapshot.getValue(Request.class);
                            Log.d("ASDASD",request1.getAmount()+"");
                            requestsList.add(request1);
                            listAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


        listAdapter.notifyDataSetChanged();






       // Toast.makeText(HomeCashProvider.this,"Starting execution",Toast.LENGTH_SHORT).show();
        fetchCordinates.execute();





    }



    private static long back_pressed=System.currentTimeMillis();
    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            fetchCordinates.cancel(true);
            fetchCordinates.onCancelled();
            finishAffinity();
        }
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

       public class FetchCordinates extends AsyncTask<String, Integer, String> {

           public FusedLocationProviderClient fusedLocationProviderClient;
           public double lati = 0.0;
            public double longi = 0.0;
            public LocationManager mLocationManager;
            public VeggsterLocationListener mVeggsterLocationListener;

            @Override
            protected void onPreExecute() {
                mVeggsterLocationListener = new VeggsterLocationListener();

                mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if (ActivityCompat.checkSelfPermission(HomeCashProvider.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeCashProvider.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mLocationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 1, 0, mVeggsterLocationListener);



            }

            @Override
            protected void onCancelled() {
                //Toast.makeText(HomeCashProvider.this,"cancel",Toast.LENGTH_LONG).show();
               mLocationManager.removeUpdates((LocationListener) mVeggsterLocationListener);
            }

            @Override
            protected void onPostExecute(String result) {

               // Toast.makeText(HomeCashProvider.this,
                 //       "LATITUDE :" + lati + " LONGITUDE :" + longi,
                   //     Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stud

                return null;
            }

            public   class VeggsterLocationListener implements LocationListener {

                @Override
                public void onLocationChanged(Location location) {

                    int lat = (int) location.getLatitude(); // * 1E6);
                    int log = (int) location.getLongitude(); // * 1E6);
                    int acc = (int) (location.getAccuracy());

                    String info = location.getProvider();
                    try {

                        // LocatorService.myLatitude=location.getLatitude();

                        // LocatorService.myLongitude=location.getLongitude();

                        lati = location.getLatitude();
                        longi = location.getLongitude();
                       // Toast.makeText(HomeCashProvider.this,"Latitude: "+lati+" Longitude: "+longi, Toast.LENGTH_LONG).show();
                        DatabaseReference forcashprovider=FirebaseDatabase.getInstance().getReference();
                        String username=data.userId;
                        forcashprovider.child("cashProviders").child(username).child("latitude").setValue(lati);
                        forcashprovider.child("cashProviders").child(username).child("longitude").setValue(longi);
                       // Toast.makeText(HomeCashProvider.this,"Location Updated !",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        // progDailog.dismiss();
                         Toast.makeText(getApplicationContext(),"Unable to get Location"
                        , Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onProviderDisabled(String provider) {
                    Log.i("OnProviderDisabled", "OnProviderDisabled");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Log.i("onProviderEnabled", "onProviderEnabled");
                }

                @Override
                public void onStatusChanged(String provider, int status,
                                            Bundle extras) {
                    Log.i("onStatusChanged", "onStatusChanged");

                }

            }

        }

    }
