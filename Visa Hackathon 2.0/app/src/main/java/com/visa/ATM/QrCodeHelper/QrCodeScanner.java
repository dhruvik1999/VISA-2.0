package com.visa.ATM.QrCodeHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.visa.ATM.R;
import com.visa.ATM.VisaDirect.TransactionResult;

import org.json.JSONObject;

public class QrCodeScanner extends AppCompatActivity {

    private IntentIntegrator qrScan;
    Double cpAmount;
    String pullJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);

        pullJson=getIntent().getStringExtra("PULL_JSON");
        cpAmount =getIntent().getDoubleExtra("AMOUNT",1000);


        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.setBeepEnabled(true);
        qrScan.initiateScan();

//        String a = "840@408999@4957030420210496@412770451018@4653459515756154";
//        String[] c = a.split("@",5);
//
//        for(String s : c){
//            Log.d("String : " , s);
//        }
//        Log.d("-------------------> : " , c[0]);



    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    final String[] resps = result.getContents().split("@");
                    for(String s : resps){
                        Log.d("String : " , s);
                    }

                    final Handler handler = new Handler();

//                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
//                    textViewName.setText(obj.getString("name"));
//                    textViewAddress.setText(obj.getString("address"));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Python py1 = Python.getInstance();
                                PyObject pyf1 = py1.getModule("push");
                                final PyObject obj1 = pyf1.callAttr("pushMoney", cpAmount, "381228649430015", resps[0], resps[1], resps[2], resps[3], resps[4]);
                                Log.d("CHECK", obj1.toString());


                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), TransactionResult.class);
                                        intent.putExtra("PUSH_JSON", obj1.toString());
                                        intent.putExtra("PULL_JSON", pullJson);
//                                intent.putExtra("PUSH_JSON",obj1.toString());
                                        startActivity(intent);
                                    }
                                });
                            }catch (Exception e){
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), TransactionResult.class);
                                        intent.putExtra("PUSH_JSON", "Failed");
                                        intent.putExtra("PULL_JSON", "Failed");
//                                intent.putExtra("PUSH_JSON",obj1.toString());
                                        startActivity(intent);
                                    }
                                });
                            }
//                        Toast.makeText(getApplicationContext(),"Transaction push", Toast.LENGTH_SHORT).show();


                        }
                    }).start();



                } catch (Exception e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}