package com.eiben.test.rxsample.operation;

import com.eiben.test.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by liumingrui on 16/9/22.
 */

public class FlatMap {

    public static Subscription flatmap() {
        Integer[] i = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return Observable.from(i)
                .flatMap(new Func1<Integer, Observable<? extends List<String>>>() {
                    @Override
                    public Observable<? extends List<String>> call(Integer integer) {
                        List<String> l = new ArrayList<String>();
                        l.add(String.valueOf(integer));
                        return Observable.just(l);
                    }
                })
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        Logger.d(strings.toString());
                    }
                });
    }
}
