package com.visa.ATM.CashProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;

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
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.visa.ATM.R;
import com.visa.ATM.data;

import java.lang.ref.Reference;


public class HomeCashProvider extends  AppCompatActivity {


    Intent locatorService = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cash_provider);
        FetchCordinates fetchCordinates=new FetchCordinates();
        fetchCordinates.execute();
    }
       public class FetchCordinates extends AsyncTask<String, Integer, String> {

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
                        LocationManager.NETWORK_PROVIDER, 0, 0, mVeggsterLocationListener);


            }

            @Override
            protected void onCancelled() {
                mLocationManager.removeUpdates((LocationListener) mVeggsterLocationListener);
            }

            @Override
            protected void onPostExecute(String result) {

                Toast.makeText(HomeCashProvider.this,
                        "LATITUDE :" + lati + " LONGITUDE :" + longi,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stub

                while (this.lati == 0.0) {

                }
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
                        Toast.makeText(HomeCashProvider.this,"Latitude: "+lati+" Longitude: "+longi, Toast.LENGTH_LONG).show();
                        DatabaseReference forcashprovider=FirebaseDatabase.getInstance().getReference();
                        String username=data.userId;
                        forcashprovider.child("cashProviders").child(username).child("latitude").setValue(lati);
                        forcashprovider.child("cashProviders").child(username).child("longitude").setValue(longi);

                    } catch (Exception e) {
                        // progDailog.dismiss();
                        // Toast.makeText(getApplicationContext(),"Unable to get Location"
                        // , Toast.LENGTH_LONG).show();
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
