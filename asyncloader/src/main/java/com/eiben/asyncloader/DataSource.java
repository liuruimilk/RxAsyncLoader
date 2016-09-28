package com.eiben.asyncloader;

import android.text.TextUtils;


import com.eiben.asyncloader.base.ITask;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by liumingrui on 16/9/23.
 */

public class DataSource {

    public Map<String, String> cache = Collections.synchronizedMap(new WeakHashMap<String, String>());

    public Observable<ITask> fromCache(final ITask task) {

        return Observable.create(new Observable.OnSubscribe<ITask>() {
            @Override
            public void call(Subscriber<? super ITask> subscriber) {
                Logger.d("from cache");

                String cacheResult = cache.get(task.getUri());
                if (TextUtils.isEmpty(cacheResult)) {
                    subscriber.onNext(null);
                } else {
                    task.setData(cacheResult);
                    subscriber.onNext(task);
                }
                subscriber.onCompleted();
            }
        });
    }

    public Observable<ITask> fromNet(final ITask task) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result = task.fromNet();
                Logger.d("from net " + result);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        }).map(new Func1<String, ITask>() {
            @Override
            public ITask call(String s) {
                if (null == s || TextUtils.isEmpty(s)) {
                    task.setErrorCode(-1);
                } else {
                    Logger.d("analysisResult net");
                    task.analysisResult(s);
                }
                return task;
            }
        }).doOnNext(new Action1<ITask>() {
            @Override
            public void call(ITask data) {
                if (data.getErrorCode() != 0) {
                    return;
                }
                cache.put(data.getUri().uri, data.getData());
            }
        });
    }
}
