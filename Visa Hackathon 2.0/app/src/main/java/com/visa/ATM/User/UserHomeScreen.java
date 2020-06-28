package com.visa.ATM.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.visa.ATM.R;
import com.visa.ATM.Request;
import com.visa.ATM.Response;
import com.visa.ATM.data;

public class UserHomeScreen extends AppCompatActivity {

    Button bLocateAtm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);

        init();
        getRequestsData();

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

    public void getRequestsData(){
        final DatabaseReference refResponse = FirebaseDatabase.getInstance().getReference().child("Users").child(data.userId).child("Responses");
        refResponse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot req : snapshot.getChildren()){
                    Response response = req.getValue(Response.class);
                    Log.d("Check : ",response.getName()+" " + response.getAmount() + " " + response.getRate()+" " +response.isStatus());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}