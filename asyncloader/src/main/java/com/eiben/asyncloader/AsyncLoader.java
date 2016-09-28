package com.eiben.asyncloader;

import android.view.View;


import com.eiben.asyncloader.base.ITask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;

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

    public void load(ITask... task) {

        List<ITask> tasks = new ArrayList<>();
        for (ITask p : task) {
            if (taskPass(p)) {
                tasks.add(p);
            }
        }
        if (tasks.size() > 0) {
            action(task);
        }
    }

    public boolean taskPass(ITask task) {
        boolean pass = true;
        View view = task.getView().get();
        if (null == view) {
            pass = false;
        }
        if (engine.containsTask(view.getId())) {
            if (engine.isNeedCancleTask(view.getId(), task.getUri().uri)) {
                engine.cancleTask(view.getId());
            } else {
                pass = false;
            }
        }
        return pass;
    }


    private void action(final ITask... task) {
        Observer observer = new Observer<ITask>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                engine.clearTask();
            }

            @Override
            public void onNext(ITask task) {
                Logger.d("action -> onNext");
                WeakReference<View> viewWeakReference = task.getView();
                if (null != viewWeakReference) {
                    View view = viewWeakReference.get();
                    if(null != view) {
                        if (task.checkDataValid()) {
                            Logger.d("action -> doSomeThingUI");
                            task.doSomeThingUI(view);
                        }
                    }
                }
                engine.cancleTask(task.getID());
                Logger.d("taskmap size : " + engine.taskMap.size());
            }
        };
        try {
            Observable<ITask> observable = engine.load(task);
            observable.subscribe(observer);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void removeCache(String url) {
        engine.dataSource.cache.remove(url);
    }

    public void clearCache() {
        engine.dataSource.cache.clear();
    }
}
