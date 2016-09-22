package com.eiben.test.rxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eiben.test.R;
import com.eiben.test.rxsample.creater.Empty;
import com.eiben.test.rxsample.creater.defer;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
//        Lift.rx();
//        defer.defer();
        Empty.empty();
    }
}
