package com.eiben.test.rxorder;

import android.text.TextUtils;

import com.eiben.test.Logger;

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
                subscriber.onNext(cache.get(data.getUrl()));
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
                String result = data.fromNet();
                if (null == result || TextUtils.isEmpty(result)) {
                    Logger.d(data.getUrl() + " net result empty");
                    data.setErrorCode(-1);
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } else {
                    data.analysisResult(result);
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }
            }
        }).doOnNext(new Action1<IData>() {
            @Override
            public void call(IData data) {
                if (data.getErrorCode() != 0) {
                    return;
                }
                Logger.d(data.getUrl() + "doOnNext");
                cache.put(data.getUrl(), data);
            }
        });
    }
}
