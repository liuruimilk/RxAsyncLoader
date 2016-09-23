package com.eiben.test.rxorder;

import android.text.TextUtils;

import com.eiben.test.Logger;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liumingrui on 16/9/23.
 */

public class DataEngine {
    private DataSource dataSource = new DataSource();

    public Observable<IData> load(IData data1, IData data2) {
        return Observable.defer(new Func0<Observable<IData>>() {
            @Override
            public Observable<IData> call() {
                return Observable.concat(getData(data1), getData(data2));
            }
        });
    }

    private Observable<IData> getData(IData data) {
        return Observable.concat(
                dataSource.fromCache(data),
                dataSource.fromNet(data))
                .first(new Func1<IData, Boolean>() {
                    @Override
                    public Boolean call(IData data) {
                        boolean flag = data != null ? (!TextUtils.isEmpty(data.getData()) ? true : false) : false;
                        Logger.d("first call : " + flag);
                        return flag;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
