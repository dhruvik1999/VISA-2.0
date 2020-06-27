package com.visa.ATM.CashProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.visa.ATM.R;


public class HomeCashProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cash_provider);

        Toast.makeText(getApplicationContext(),"Home cash provider",Toast.LENGTH_SHORT).show();
    }
}