package com.eiben.test.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eiben.test.R;

import rx.Observable;
import rx.Subscriber;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);


        Observable.create((Observable.OnSubscribe<String>) subscriber -> {

        });
    }
}
