package com.eiben.test.rxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eiben.test.R;
import com.eiben.test.rxsample.creater.Empty;
import com.eiben.test.rxsample.creater.Interval;
import com.eiben.test.rxsample.creater.defer;
import com.eiben.test.rxsample.operation.FlatMap;

import rx.Subscription;

public class RxActivity extends AppCompatActivity {
private Subscription subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
//        Lift.rx();
//        defer.defer();
//        Empty.empty();
//        subscription = Interval.interval();
          subscription = FlatMap.flatmap();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != subscription && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
