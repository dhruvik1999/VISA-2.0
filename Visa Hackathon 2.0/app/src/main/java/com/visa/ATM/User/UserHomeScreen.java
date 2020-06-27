package com.visa.ATM.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.visa.ATM.R;

public class UserHomeScreen extends AppCompatActivity {

    Button bLocateAtm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);

        init();

        bLocateAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GoogleMap.class));
            }
        });

    }

    public void init(){
        bLocateAtm = this.findViewById(R.id.b_locate_atm);
    }
}