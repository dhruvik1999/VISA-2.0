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

    TextView pullRes,pushRes,fres;
    Button bBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_result);

        String pullJson=getIntent().getStringExtra("PULL_JSON");
        String pushJson=getIntent().getStringExtra("PUSH_JSON");

        String response = "";
        String tid = "";
        init();

        try {
            Gson g = new Gson();
            PullPojo pull = g.fromJson(pullJson, PullPojo.class);
            Toast.makeText(getApplicationContext(),pull.getTransactionIdentifier(),Toast.LENGTH_LONG).show();
            response = "Pull Transaction:\n Transaction Identifier\n" + pull.getTransactionIdentifier();
            tid = pull.getTransactionIdentifier();
            pullRes.setText(pull.getTransactionIdentifier());
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Pull transaction failed",Toast.LENGTH_LONG).show();
            pullRes.setText("Payment Failed !");
            fres.setText("Payment Failed !");
        }finally {
            try {
                Gson g = new Gson();
                PushPojo push = g.fromJson(pushJson, PushPojo.class);
//                Toast.makeText(getApplicationContext(), push.getTransactionIdentifier(), Toast.LENGTH_LONG).show();
//                response = response + "\n\n" + "Push Transaction: " + "\n" + " Transaction Identifier\n" + push.getTransactionIdentifier();
                pushRes.setText(push.getTransactionIdentifier());
            }catch (Exception e){
                e.printStackTrace();
//                Toast.makeText(getApplicationContext(),"Pull transaction failed",Toast.LENGTH_LONG).show();
                pushRes.setText("Payment Failed !");
                fres.setText("Payment Failed !");

            }finally {
//                .setText(response);
                fres.setText("Payment Successful !  ");
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
//        tvResult = this.findViewById(R.id.tv_results);
        bBack = this.findViewById(R.id.b_back);
        pullRes = this.findViewById(R.id.pull_tp);
        pushRes = this.findViewById(R.id.push_tp);
        fres = this.findViewById(R.id.fres);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}