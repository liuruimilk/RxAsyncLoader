package com.eiben.test.rxorder;

import android.text.TextUtils;

import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by liumingrui on 16/9/23.
 */

public class Source {
    public ConcurrentHashMap<String, Data> cache = new ConcurrentHashMap<String, Data>();

    public Observable<Data> fromCache(String key) {
        return Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {
                Data data = cache.get(key);
                if (null != data) {
                    data.setValue("data from cache");
                }
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<Data> fromNet(String url) {
        return Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result = "data from net";
                if (null == result || TextUtils.isEmpty(result)) {
                    subscriber.onError(new Exception("net failed"));
                } else {
                    subscriber.onNext(new Data(result));
                    subscriber.onCompleted();
                }
            }
        }).doOnNext(new Action1<Data>() {
            @Override
            public void call(Data data) {
                cache.put(url, data);
            }
        });
    }
}
