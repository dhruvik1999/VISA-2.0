package com.visa.ATM.VisaDirect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.visa.ATM.R;
import com.visa.ATM.User.UserHomeScreen;

public class TransactionResult extends AppCompatActivity {

    TextView tvResult;
    Button bBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_result);

        String pullJson=getIntent().getStringExtra("PULL_JSON");
        String pushJson=getIntent().getStringExtra("PUSH_JSON");

        String response = "";
        init();

        try {
            Gson g = new Gson();
            PullPojo pull = g.fromJson(pullJson, PullPojo.class);
            Toast.makeText(getApplicationContext(),pull.getTransactionIdentifier(),Toast.LENGTH_LONG).show();
            response = "Pull Transaction:\n Transaction Identifier" + pull.getTransactionIdentifier();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Pull transaction failed",Toast.LENGTH_LONG).show();
            tvResult.setText("transaction Failed. \n Try again latter");
        }finally {
            try {
                Gson g = new Gson();
                PushPojo push = g.fromJson(pushJson, PushPojo.class);
                Toast.makeText(getApplicationContext(), push.getTransactionIdentifier(), Toast.LENGTH_LONG).show();
                response = response + "\n\n" + "Push Transaction: " + "\n" + " Transaction Identifier\n" + push.getTransactionIdentifier();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Pull transaction failed",Toast.LENGTH_LONG).show();
                tvResult.setText("transaction Failed. \n Try again latter");
            }finally {
                tvResult.setText(response);
            }
        }

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UserHomeScreen.class));
            }
        });
    }

    private void init(){
        tvResult = this.findViewById(R.id.tv_results);
        bBack = this.findViewById(R.id.b_back);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}