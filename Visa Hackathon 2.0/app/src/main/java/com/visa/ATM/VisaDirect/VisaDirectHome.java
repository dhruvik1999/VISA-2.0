package com.visa.ATM.VisaDirect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.visa.ATM.R;

public class VisaDirectHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_direct_home);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        PyObject pyf = py.getModule("pull");
        PyObject obj = pyf.callAttr("pullMoney",1000);
//        Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_LONG).show();
        Log.d("CHECK1",obj.toString());

        Python py1 = Python.getInstance();
        PyObject pyf1 = py1.getModule("push");
        PyObject obj1 = pyf1.callAttr("pushMoney",1000);
        Log.d("CHECK2",obj1.toString());
//        Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_LONG).show();

//        Log.d("CHECK",obj.toString());

    }
}