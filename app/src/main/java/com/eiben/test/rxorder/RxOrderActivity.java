package com.eiben.test.rxorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eiben.test.Logger;
import com.eiben.test.R;
import com.google.gson.Gson;

import rx.Observer;

public class RxOrderActivity extends AppCompatActivity {

    DataEngine engine = new DataEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_order);
    }

    public void click(View v) {
        engine.load(new Price("com.eiben"), new Price("com.zhouxue")).subscribe(new Observer<IData>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage());
            }

            @Override
            public void onNext(IData data) {
                Logger.d(data.getData());
            }
        });
    }
}
