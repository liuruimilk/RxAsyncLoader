package com.eiben.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.eiben.test.rxmvp.ui.UserActivity;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        publishSubject();
    }

    public void button1(View view) {
        startActivity(new Intent(this, UserActivity.class));
    }

    private void observableCreate() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("you");
                subscriber.onNext("can");
                subscriber.onNext("not");
                subscriber.onNext("see");
                subscriber.onNext("me");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        subscription = observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("liumingrui", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("liumingrui", "onError");
            }

            @Override
            public void onNext(String s) {
                Log.d("liumingrui", s);
            }
        });
    }

    private void observableFrom() {
        String[] str = new String[]{"hello", "world"};
        Observable<String> observable = Observable.from(str);
        subscription = observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("liumingrui", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("liumingrui", "onError");
            }

            @Override
            public void onNext(String s) {
                Log.d("liumingrui", s);
            }
        });
    }

    private void observableJust() {
        String[] str = new String[]{"hello", "world"};
        Observable<String[]> observable = Observable.just(str);
        subscription = observable.subscribe(new Observer<String[]>() {
            @Override
            public void onCompleted() {
                Log.d("liumingrui", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("liumingrui", "onError");
            }

            @Override
            public void onNext(String[] s) {
                for (String s1 : s) {
                    Log.d("liumingrui", s1);
                }
            }
        });
    }

    private void publishSubject() {
        PublishSubject<String> publishSubject = PublishSubject.create();

        subscription = publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("liumingrui", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("liumingrui", s);
            }
        });

        publishSubject.onNext("hello");
        publishSubject.onNext("world");
        publishSubject.onCompleted();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != subscription && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
    }
}
