package com.visa.ATM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.visa.ATM.QrCodeHelper.QrCodeScanner;

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
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.putExtra("option","cp");
                startActivity(i);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.putExtra("option","us");
                startActivity(i);
            }
        });
    }

    private void init(){
        cashProvider = this.findViewById(R.id.b_cashProvide);
        user = this.findViewById(R.id.b_user);
    }
}
