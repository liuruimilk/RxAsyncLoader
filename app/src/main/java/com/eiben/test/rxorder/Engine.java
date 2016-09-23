package com.eiben.test.rxorder;

import android.text.TextUtils;

import com.eiben.test.Logger;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liumingrui on 16/9/23.
 */

public class Engine {
    Source source = new Source();

    public Observable<Data> getData(String url) {
        Observable<Data> observable = Observable.concat(
                source.fromCache(url),
                source.fromNet(url))
                .first(new Func1<Data, Boolean>() {
                    @Override
                    public Boolean call(Data data) {
                        boolean flag = data != null ? (!TextUtils.isEmpty(data.getValue()) ? true : false) : false;
                        Logger.d("first call : " + flag);
                        return flag;
                    }
                });

        return Observable.just(null)
                .flatMap(new Func1<Object, Observable<Data>>() {
                    @Override
                    public Observable<Data> call(Object o) {
                        return observable;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
