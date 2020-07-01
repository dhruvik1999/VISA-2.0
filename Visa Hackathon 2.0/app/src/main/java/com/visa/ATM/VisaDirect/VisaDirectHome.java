package com.visa.ATM.VisaDirect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.visa.ATM.QrCodeHelper.QrCodeScanner;
import com.visa.ATM.R;

public class VisaDirectHome extends AppCompatActivity {

    TextView tvName,tvRate,etAmount;
    Button bPay;
    public double cpAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_direct_home);

        init();

        String cpName = getIntent().getStringExtra("NAME");
        cpAmount =  Double.parseDouble(getIntent().getStringExtra("AMOUNT").toString());
        double cpRate = Double.parseDouble(getIntent().getStringExtra("RATE").toString());

        cpAmount = (1+cpRate/100)*cpAmount;

        tvName.setText( cpName );
        etAmount.setText( String.valueOf(cpAmount));
        tvRate.setText(String.valueOf(cpRate));

        bPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(!Python.isStarted())
                            Python.start(new AndroidPlatform(VisaDirectHome.this));

                        Python py = Python.getInstance();
                        PyObject pyf = py.getModule("pull");
                        final PyObject obj = pyf.callAttr("pullMoney",cpAmount);
                        Log.d("CHECK1",obj.toString());
//                        Toast.makeText(getApplicationContext(),"Transaction pull", Toast.LENGTH_SHORT).show();

//                        Python py1 = Python.getInstance();
//                        PyObject pyf1 = py1.getModule("push");
//                        final PyObject obj1 = pyf1.callAttr("pushMoney",cpAmount);
//                        Log.d("CHECK",obj1.toString());
////                        Toast.makeText(getApplicationContext(),"Transaction push", Toast.LENGTH_SHORT).show();
//
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Intent intent = new Intent( getApplicationContext() , TransactionResult.class );
//                                intent.putExtra("PULL_JSON",obj.toString());
//                                intent.putExtra("PUSH_JSON",obj1.toString());
//                                startActivity(intent);
//                            }
//                        });

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent( getApplicationContext() , QrCodeScanner.class );
                                intent.putExtra("PULL_JSON",obj.toString());
                                intent.putExtra("AMOUNT",cpAmount);
//                                intent.putExtra("PUSH_JSON",obj1.toString());
                                startActivity(intent);
                            }
                        });


                    }
                }).start();



            }
        });

    }

    private void init(){
        tvName = this.findViewById(R.id.tv_name);
        tvRate = this.findViewById(R.id.tv_rate);
        etAmount = this.findViewById(R.id.et_amount);
        bPay = this.findViewById(R.id.b_send);
    }

}

//        if(!Python.isStarted())
//            Python.start(new AndroidPlatform(this));
//
//        Python py = Python.getInstance();
//        PyObject pyf = py.getModule("pull");
//        PyObject obj = pyf.callAttr("pullMoney",1000);
////        Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_LONG).show();
//        Log.d("CHECK1",obj.toString());
//
//        Python py1 = Python.getInstance();
//        PyObject pyf1 = py1.getModule("push");
//        PyObject obj1 = pyf1.callAttr("pushMoney",1000);
//        Log.d("CHECK2",obj1.toString());
////        Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_LONG).show();
//
////        Log.d("CHECK",obj.toString());