package com.eiben.test.rxorder;

import android.text.TextUtils;

import com.eiben.test.Logger;

/**
 * Created by liumingrui on 16/9/23.
 */

public class AsyncLoader {


    private static class Holder {
        static final AsyncLoader loader = new AsyncLoader();
    }

    public static AsyncLoader getInstance() {
        return Holder.loader;
    }


    private DataEngine engine;

    private AsyncLoader() {
        engine = new DataEngine();
    }

    public void load(IData... data) {
        DataEngine.CallBack callBack = new DataEngine.CallBack() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage());
            }

            @Override
            public void onNext(Price data) {
                Logger.d(data.toString());
                if (!TextUtils.isEmpty(data.getData())) {
                }
            }

            @Override
            public void onNext(Address data) {
                Logger.d(data.toString());
                if (!TextUtils.isEmpty(data.getData())) {
                }
            }

            @Override
            public void onNext(User data) {
                Logger.d(data.toString());
                if (!TextUtils.isEmpty(data.getData())) {
                }
            }
        };
        engine.load(callBack, data);
    }
}
