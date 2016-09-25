package com.eiben.test.rxorder.model;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by liumingrui on 16/9/23.
 */

public interface IData {

    String getUrl();

    String getData();

    void setErrorCode(int code);

    int getErrorCode();

    WeakReference<View> getView();

    void analysisResult(String result);

    void doSomeThing(View v);
}
