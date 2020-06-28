package com.visa.ATM.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.visa.ATM.CashProviders;
import com.visa.ATM.R;
import com.visa.ATM.Request;
import com.visa.ATM.data;

public class RequestHelper extends AppCompatActivity {

    TextView tvName,tvRate,etAmount;
    Button bSend;

    String name,rate,amount;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_helper);

        init();

        String atmName = getIntent().getStringExtra("ATM_NAME");

        name = "";
        for(int i=10;i<atmName.length();i++){
            name = name+atmName.charAt(i);
        }
        reference = FirebaseDatabase.getInstance().getReference().child("cashProviders").child(name);
        Toast.makeText(getApplicationContext(),"-"+name+"-",Toast.LENGTH_SHORT).show();
        fillData(name);


        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send request to the cash provider
                Request request = new Request();
                request.setAmount( Double.parseDouble(etAmount.getText().toString()) );
                request.setRate((double)1.2);
                request.setName(data.userId);

                reference.child("Requests").child(data.userId).setValue(request);
            }
        });



    }

    private void init(){
        tvName = this.findViewById(R.id.tv_name);
        tvRate = this.findViewById(R.id.tv_rate);
        etAmount = this.findViewById(R.id.et_amount);
        bSend = this.findViewById(R.id.b_send);
    }

    public void fillData(String name){

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CashProviders cashProviders = dataSnapshot.getValue(CashProviders.class);
                tvName.setText( cashProviders.getName() );
                tvRate.setText( "1.2%" );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}