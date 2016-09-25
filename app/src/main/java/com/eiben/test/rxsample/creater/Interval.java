package com.eiben.test.rxsample.creater;

import com.eiben.test.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by liumingrui on 16/9/22.
 */

public class Interval {

    public static Subscription interval() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Logger.d("" + aLong);
                    }
                });
    }
}
