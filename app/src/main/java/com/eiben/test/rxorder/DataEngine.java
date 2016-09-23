package com.eiben.test.rxorder;


import android.text.TextUtils;

import com.eiben.test.Logger;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liumingrui on 16/9/24.
 */

public class DataEngine {
    private DataSource dataSource = new DataSource();

    public void load(IData data1, IData data2, CallBack call) {
        load(data1, data2)
                .subscribe(new Observer<IData>() {
                    @Override
                    public void onCompleted() {
                        call.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        call.onError(e);
                    }

                    @Override
                    public void onNext(IData data) {
                        if (data instanceof Price) {
                            Price p = (Price) data;
                            call.onNext(p);
                        }
                        if (data instanceof Address) {
                            Address a = (Address) data;
                            call.onNext(a);
                        }
                    }
                });
    }

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

    public interface CallBack {
        void onCompleted();

        void onError(Throwable e);

        void onNext(Price data);

        void onNext(Address data);
    }
}
