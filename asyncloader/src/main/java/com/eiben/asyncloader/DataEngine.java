package com.eiben.asyncloader;



import android.text.TextUtils;

import com.eiben.asyncloader.base.ITask;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liumingrui on 16/9/24.
 */

public class DataEngine {
    /**
     * key:view id
     * value:url
     */
    Map<Integer, String> taskMap = Collections
            .synchronizedMap(new HashMap<Integer, String>());

    /**
     * key:group的唯一ID,订单页采用订单号
     * value:view id 合集
     */
    Map<Integer, List<Integer>> groupTaskMap = Collections
            .synchronizedMap(new HashMap<Integer, List<Integer>>());

    DataSource dataSource = new DataSource();

    public boolean containsTask(int key) {
        return taskMap.containsKey(key);
    }

    public void clearTask() {
        taskMap.clear();
    }

    public void cancleTask(int key) {
        taskMap.remove(key);
    }

    public boolean isCancleTask(int key) {
        return !containsTask(key);
    }

    public boolean isNeedCancleTask(int key, String url) {
        boolean flag = true;
        String value = taskMap.get(key);
        if (TextUtils.isEmpty(value)) {
            flag = false;
        } else if (!value.equals(url)) {
            flag = false;
        }

        return flag;
    }

    public Observable<ITask> load(final ITask... tasks) throws IllegalArgumentException {
        final Observable<ITask> observable = matchObservable(tasks);
        if (null != observable) {
            return Observable.defer(new Func0<Observable<ITask>>() {
                @Override
                public Observable<ITask> call() {
                    return observable;
                }
            })
                    .doOnNext(new Action1<ITask>() {
                        @Override
                        public void call(ITask iTask) {
                            if (iTask.checkDataValid()) {
                                iTask.doSomeThingIO();
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            throw new IllegalArgumentException("参数超出支持范围");
        }
    }

    private Observable<ITask> matchObservable(ITask... task) {
        if (null == task || task.length == 0) {
            return null;
        }
        Observable<ITask> temp = null;
        if (task.length == 1) {
            temp = load(task[0]);
        } else if (task.length == 2) {
            temp = load(task[0], task[1]);
        } else if (task.length == 3) {
            temp = load(task[0], task[1], task[2]);
        }
        return temp;
    }

    private Observable<ITask> load(ITask data) {
        return getData(data);
    }

    private Observable<ITask> load(ITask data1, ITask data2) {
        return Observable.concat(getData(data1), getData(data2));
    }

    private Observable<ITask> load(ITask data1, ITask data2, ITask data3) {
        return Observable.concat(getData(data1), getData(data2), getData(data3));
    }

    private Observable<ITask> load(ITask data1, ITask data2, ITask data3, ITask data4) {
        return Observable.concat(getData(data1), getData(data2), getData(data3), getData(data4));
    }

    private Observable<ITask> getData(ITask data) {
        return Observable.concat(
                dataSource.fromCache(data),
                dataSource.fromNet(data))
                .first(new Func1<ITask, Boolean>() {
                    @Override
                    public Boolean call(ITask data) {
                        boolean flag = (data == null ? false : true);
                        Logger.d("first : " + flag);
                        return flag;
                    }
                });

    }
}
