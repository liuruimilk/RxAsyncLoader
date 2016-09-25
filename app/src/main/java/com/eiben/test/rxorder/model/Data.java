package com.eiben.test.rxorder.model;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by liumingrui on 16/9/23.
 */

public abstract class Data implements IData {

    protected String url;
    protected String data = "";
    protected int errorCode = 0;
    protected WeakReference<View> view;

    @Override
    public WeakReference<View> getView() {
        return view;
    }

    public void setView(View view) {
        this.view = new WeakReference<>(view);
    }

    public Data(String url, View v) {
        this.url = url;
        this.view = new WeakReference<>(v);
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
