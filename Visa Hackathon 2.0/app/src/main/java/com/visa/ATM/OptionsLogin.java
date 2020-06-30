package com.visa.ATM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsLogin extends AppCompatActivity {

    Button cashProvider,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_login);
        //hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        cashProvider = this.findViewById(R.id.b_cashProvider);
        user = this.findViewById(R.id.b_user);
    }
    private static long back_pressed=System.currentTimeMillis();
    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis())
            finishAffinity();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
