package com.eiben.test.rxorder.loader;

import android.text.TextUtils;
import android.view.View;

import com.eiben.test.Logger;
import com.eiben.test.rxorder.loader.base.IParam;

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

    public void load(IParam... param) {
        Observer observer = new Observer<IParam>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage());
            }

            @Override
            public void onNext(IParam param) {
                Logger.d(param.toString());
                if (!TextUtils.isEmpty(param.getData())) {
                    WeakReference<View> viewWeakReference = engine.viewsHolder.cache.get(param.getUrl());
                    if (null != viewWeakReference) {
                        View view = viewWeakReference.get();
                        if (view != null) {
                            param.doSomeThing(view);
                        }
                    }
                }
            }
        };
        try {
            Observable<IParam> observable = engine.load(param);
            observable.subscribe(observer);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
