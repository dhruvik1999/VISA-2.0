package com.visa.ATM.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.visa.ATM.CashProvider.MyAdapter;
import com.visa.ATM.R;
import com.visa.ATM.Request;
import com.visa.ATM.Response;
import com.visa.ATM.data;

import java.util.ArrayList;

public class UserHomeScreen extends AppCompatActivity {

    Button bLocateAtm;
    ArrayList<Response> responses;
    UserAdapter listAdapter;
    LinearLayoutManager layoutManager;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_home_screen);

        init();
        getRequestsData();

        bLocateAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GoogleMap.class));
            }
        });

        recycler = findViewById(R.id.user_recycler);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        listAdapter = new UserAdapter(responses, this);
        recycler.setAdapter(listAdapter);

    }

    public void init(){
        bLocateAtm = this.findViewById(R.id.b_locate_atm);
        responses = new ArrayList<>();
    }

    public void getRequestsData(){
        final DatabaseReference refResponse = FirebaseDatabase.getInstance().getReference().child("Users").child(data.userId).child("Responses");
        refResponse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                responses.clear();
                for (DataSnapshot req : snapshot.getChildren()){
                    Response response = req.getValue(Response.class);
                    Log.d("Check : ",response.getName()+" " + response.getAmount() + " " + response.getRate()+" " +response.isStatus());
                    responses.add(response);
                    listAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}