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
        PyObject pyf = py.getModule("script");
        PyObject obj = pyf.callAttr("callVisaDirect");
        Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_LONG).show();

        Log.d("CHECK",obj.toString());

    }
}