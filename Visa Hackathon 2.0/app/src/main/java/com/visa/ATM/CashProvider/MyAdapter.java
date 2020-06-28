package com.visa.ATM.CashProvider;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.visa.ATM.R;
import com.visa.ATM.Request;
import com.visa.ATM.Users;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactHolder> {

    // List to store all the contact details
    private ArrayList<Request> requestsList;
    private Context mContext;

    // Counstructor for the Class
    public MyAdapter(ArrayList<Request> contactsList, Context context) {
        this.requestsList = contactsList;
        this.mContext = context;
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.recycler_custom, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public int getItemCount() {
        return requestsList == null? 0: requestsList.size();
    }

    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, final int position) {
        final Request request = requestsList.get(position);

        // Set the data to the views here
        holder.setUserName(request.getName());
        holder.setUserAmount(request.getAmount()+"");
        Log.d("Recycle",request.getAmount()+"");

        // You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public

    }

    // This is your ViewHolder class that helps to populate data to the view
    public class ContactHolder extends RecyclerView.ViewHolder {

        private TextView user_name;
        private TextView amount;

        public ContactHolder(View itemView) {
            super(itemView);

            user_name = itemView.findViewById(R.id.user_name);
            amount = itemView.findViewById(R.id.amount);
        }

        public void setUserName(String name) {
            user_name.setText(name);
        }

        public void setUserAmount(String number) {
            amount.setText(number);
        }
    }
}
