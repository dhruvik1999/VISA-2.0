package com.visa.ATM.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.visa.ATM.R;
import com.visa.ATM.Response;
import com.visa.ATM.data;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ContactHolder> {
    ArrayList<Response> responses;
    Context context;
    public UserAdapter(ArrayList<Response> responses, Context context){
        this.responses=responses;
        this.context=context;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_user_response, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Response response = responses.get(position);

        holder.setAmount(response.getAmount());
        holder.setCpName(response.getName());
        holder.setCpRate(response.getRate());
        holder.setCpStatus(response.isStatus());

    }

    @Override
    public int getItemCount() {
        return responses == null? 0: responses.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {

        private TextView tvAmount,tvCpName,tvCpRate,tvCpStatus;
        private Button bPay,bDelete;

        String cashProviderName;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);

            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvCpName = itemView.findViewById(R.id.tv_cp_name);
            tvCpRate = itemView.findViewById(R.id.tv_cp_rate);
            tvCpStatus = itemView.findViewById(R.id.tv_cp_status);

            bPay = itemView.findViewById(R.id.b_pay);
            bDelete = itemView.findViewById(R.id.b_delete);

            bPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context,PayHelper.class));
                }
            });

            bDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(data.userId).child("Responses").child(cashProviderName).removeValue();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    notifyDataSetChanged();
                }
            });

        }

        public void setAmount(Double amount){
            tvAmount.setText(String.valueOf(amount));
        }
        public void setCpName(String name){
            tvCpName.setText(name);
            cashProviderName=name;
        }
        public void setCpRate(double rate){
            tvCpRate.setText(String.valueOf(rate));
        }
        public void setCpStatus(boolean status ){
            if(status){
                tvCpStatus.setText("Accepted");
            }else{
                tvCpStatus.setText("Declined");
            }
        }


    }
}
