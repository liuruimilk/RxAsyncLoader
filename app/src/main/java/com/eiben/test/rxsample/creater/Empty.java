package com.eiben.test.rxsample.creater;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liumingrui on 16/9/22.
 */

public class Empty {
    public static void empty() {
        Observable.empty()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("liumingrui", "doOnSubscribe");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.d("liumingrui", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("liumingrui", "onError");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d("liumingrui", "onNext");
                    }
                });
    }
}
