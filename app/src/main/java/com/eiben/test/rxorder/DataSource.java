package com.eiben.test.rxorder;

import android.text.TextUtils;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by liumingrui on 16/9/23.
 */

public class DataSource {
    public Map<String, IData> cache = Collections.synchronizedMap(new WeakHashMap<String, IData>());

    public Observable<IData> fromCache(IData data) {
        return Observable.create(new Observable.OnSubscribe<IData>() {
            @Override
            public void call(Subscriber<? super IData> subscriber) {
                IData temp = cache.get(data.getUrl());
                if (null != temp) {
                    temp.setData(data.getUrl() + " IData from cache");
                    temp.setUrl(data.getUrl());
                }
                subscriber.onNext(temp);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<IData> fromNet(IData data) {
        return Observable.create(new Observable.OnSubscribe<IData>() {
            @Override
            public void call(Subscriber<? super IData> subscriber) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result = data.getUrl() + " IData from net";
                if (null == result || TextUtils.isEmpty(result)) {
                    subscriber.onError(new Exception("net failed"));
                } else {
                    data.setData(result);
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }
            }
        }).doOnNext(new Action1<IData>() {
            @Override
            public void call(IData data) {
                cache.put(data.getUrl(), data);
            }
        });
    }
}
