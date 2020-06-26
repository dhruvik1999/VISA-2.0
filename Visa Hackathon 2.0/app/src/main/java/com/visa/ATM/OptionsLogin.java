package com.visa.ATM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.visa.ATM.CashProvider.HomeCashProvider;
import com.visa.ATM.User.GoogleMap;


public class OptionsLogin extends AppCompatActivity {

    Button cashProvider,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_login);

        init();

        cashProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeCashProvider.class));
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GoogleMap.class));
            }
        });
    }

    private void init(){
        cashProvider = this.findViewById(R.id.b_cashProvide);
        user = this.findViewById(R.id.b_user);
    }
}
