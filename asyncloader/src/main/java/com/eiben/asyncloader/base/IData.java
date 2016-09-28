package com.eiben.asyncloader.base;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by liumingrui on 16/9/25.
 */

public interface IData {

    LoadUri getUri();

    void setData(String data);

    String getData();

    void setErrorCode(int code);

    int getErrorCode();

    WeakReference<View> getView();
}
