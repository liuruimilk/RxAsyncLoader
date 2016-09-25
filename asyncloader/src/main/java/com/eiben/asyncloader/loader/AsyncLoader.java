package com.eiben.asyncloader.loader;

import android.text.TextUtils;
import android.view.View;


import com.eiben.asyncloader.loader.base.IParam;

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
    }

    public void load(IParam... param) {
        Observer observer = new Observer<IParam>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(IParam param) {
                if (!TextUtils.isEmpty(param.getData())) {
                    WeakReference<View> viewWeakReference = param.getView();
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
