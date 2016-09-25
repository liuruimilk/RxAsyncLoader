package com.eiben.test.rxorder;


import android.icu.util.ICUUncheckedIOException;
import android.text.TextUtils;

import com.eiben.test.Logger;
import com.eiben.test.rxorder.model.Address;
import com.eiben.test.rxorder.model.IData;
import com.eiben.test.rxorder.model.Price;
import com.eiben.test.rxorder.model.User;

import org.w3c.dom.Text;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liumingrui on 16/9/24.
 */

public class DataEngine {
    DataSource dataSource = new DataSource();
    ViewsHolder viewsHolder = new ViewsHolder();

    public Observable<IData> load(IData... data) throws IllegalArgumentException{
        Observable<IData> temp = null;
        if (data.length == 2) {
            temp = load(data[0], data[1]);
        } else if (data.length == 3) {
            temp = load(data[0], data[1], data[2]);
        }
        if (null != temp) {
            Observable<IData> observable = temp;
            return Observable.defer(new Func0<Observable<IData>>() {
                @Override
                public Observable<IData> call() {
                    return observable;
                }
            })
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            for (IData d : data) {
                                viewsHolder.cache.put(d.getUrl(), d.getView());
                            }
                        }
                    })
                    .doOnNext(new Action1<IData>() {
                        @Override
                        public void call(IData iData) {
                            if (TextUtils.isEmpty(iData.getData())) {
                                viewsHolder.cache.remove(iData.getUrl());
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }else {
            throw new IllegalArgumentException("参数超出支持范围");
        }
    }


    private Observable<IData> load(IData data1, IData data2) {
        return Observable.concat(getData(data1), getData(data2));
    }

    private Observable<IData> load(IData data1, IData data2, IData data3) {
        return Observable.concat(getData(data1), getData(data2), getData(data3));
    }

    private Observable<IData> getData(IData data) {
        return Observable.concat(
                dataSource.fromCache(data),
                dataSource.fromNet(data))
                .first(new Func1<IData, Boolean>() {
                    @Override
                    public Boolean call(IData data) {
                        boolean flag = (data == null ? false : true);
                        Logger.d("filter : " + flag);
                        return flag;
                    }
                });

    }
}
