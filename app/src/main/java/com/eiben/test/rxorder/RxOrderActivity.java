package com.eiben.test.rxorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eiben.test.Logger;
import com.eiben.test.R;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxOrderActivity extends AppCompatActivity {

    Engine engine = new Engine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_order);
    }

    public void click(View v) {
        engine.getData("com.eiben").subscribe(new Observer<Data>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage());
            }

            @Override
            public void onNext(Data data) {
                Logger.d(data.getValue());
            }
        });
    }
}
