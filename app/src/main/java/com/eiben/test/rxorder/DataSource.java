package com.eiben.test.rxorder;

import android.text.TextUtils;

import com.eiben.test.Logger;
import com.eiben.test.rxorder.model.IParam;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by liumingrui on 16/9/23.
 */

public class DataSource {
    static String[] datas = {
            "net 1",
            "net 2",
            "net 3",
            "",
            "net 5",
            "",
            "net 7",
            "net 8",
            "net 9",
            "",
            "net 11",
            "net 12",
            "net 13",
            "",
            "net 15",
    };

    public Map<String, IParam> cache = Collections.synchronizedMap(new WeakHashMap<String, IParam>());

    public Observable<IParam> fromCache(IParam data) {
        return Observable.create(new Observable.OnSubscribe<IParam>() {
            @Override
            public void call(Subscriber<? super IParam> subscriber) {
                subscriber.onNext(cache.get(data.getUrl()));
                subscriber.onCompleted();
            }
        });
    }

    public Observable<IParam> fromNet(IParam data) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Random rand = new Random();
                int i = rand.nextInt(15);
                String result = datas[i];
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        }).map(new Func1<String, IParam>() {
            @Override
            public IParam call(String s) {
                if (null == s || TextUtils.isEmpty(s)) {
                    data.setErrorCode(-1);
                } else {
                    data.analysisResult(s);
                }
                return data;
            }
        }).doOnNext(new Action1<IParam>() {
            @Override
            public void call(IParam data) {
                if (data.getErrorCode() != 0) {
                    return;
                }
                Logger.d(data.getUrl() + " doOnNext");
                cache.put(data.getUrl(), data);
            }
        });
    }
}
