package com.eiben.test.rxorder.loader.base;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by liumingrui on 16/9/25.
 */

public interface IData {

    String getUrl();

    void setData(String data);

    String getData();

    void setErrorCode(int code);

    int getErrorCode();

    WeakReference<View> getView();
}
