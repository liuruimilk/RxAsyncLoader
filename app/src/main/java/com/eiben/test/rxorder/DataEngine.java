package com.eiben.test.rxorder;



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

    public void load(CallBack call, IData... data) {
        Observable<IData> temp = null;
        if (data.length == 2) {
            temp = load(data[0], data[1]);
        } else if (data.length == 3) {
            temp = load(data[0], data[1], data[2]);
        }
        if (null != temp) {
            Observable<IData> observable = temp;
            Observable.defer(new Func0<Observable<IData>>() {
                @Override
                public Observable<IData> call() {
                    return observable;
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
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
                            if (data instanceof User) {
                                User u = (User) data;
                                call.onNext(u);
                            }
                        }
                    });
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

    public interface CallBack {
        void onCompleted();

        void onError(Throwable e);

        void onNext(Price data);

        void onNext(Address data);

        void onNext(User data);
    }
}
