package com.eiben.test.rxorder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eiben.test.Logger;
import com.eiben.test.rxorder.model.Address;
import com.eiben.test.rxorder.model.IData;
import com.eiben.test.rxorder.model.Price;
import com.eiben.test.rxorder.model.User;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Observer;

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

    public void clearCache() {
        engine.dataSource.cache.clear();
        engine.viewsHolder.cache.clear();
    }

    public void load(IData... data) {
        Observer observer = new Observer<IData>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage());
            }

            @Override
            public void onNext(IData data) {
                Logger.d(data.toString());
                if (!TextUtils.isEmpty(data.getData())) {
                    WeakReference<View> viewWeakReference = engine.viewsHolder.cache.get(data.getUrl());
                    if (null != viewWeakReference) {
                        View view = viewWeakReference.get();
                        if (view != null) {
                            data.doSomeThing(view);
                        }
                    }
                }
            }
        };
        try {
            Observable<IData> observable = engine.load(data);
            observable.subscribe(observer);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
