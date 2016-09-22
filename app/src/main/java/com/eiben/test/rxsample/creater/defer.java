package com.eiben.test.rxsample.creater;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * Created by liumingrui on 16/9/22.
 */

public class defer {
    public static void defer() {
        User user = new User();
        Observable<String> observable = user.getName();
        user.setName("zhouxue");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("liumingrui", s);
            }
        });
    }

    static class User {
        String name;

        public Observable<String> getName() {
            return Observable.defer(new Func0<Observable<String>>() {
                @Override
                public Observable<String> call() {
                    return Observable.just(name);
                }
            });
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
