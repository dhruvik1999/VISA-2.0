package com.visa.ATM.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.visa.ATM.R;

public class RequestHelper extends AppCompatActivity {

    TextView tvName,tvRate,etAmount;
    Button bSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_helper);

        init();
        fillData();

        String atmName = getIntent().getStringExtra("ATM_NAME");
        Toast.makeText(getApplicationContext(),atmName,Toast.LENGTH_SHORT).show();

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send request to the cash provider
            }
        });



    }

    private void init(){
        tvName = this.findViewById(R.id.tv_name);
        tvRate = this.findViewById(R.id.tv_rate);
        etAmount = this.findViewById(R.id.et_amount);
        bSend = this.findViewById(R.id.b_send);
    }

    public void fillData(){

    }

}