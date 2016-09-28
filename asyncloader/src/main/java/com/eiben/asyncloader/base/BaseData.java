package com.eiben.asyncloader.base;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by liumingrui on 16/9/25.
 */

public class BaseData implements IData {

    protected int id;
    protected LoadUri uri;
    protected String data = "";
    protected int errorCode = 0;
    protected WeakReference<View> view;

    public BaseData(LoadUri uri, View view) {
        this.id = view.getId();
        this.uri = uri;
        this.view = new WeakReference<>(view);
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public LoadUri getUri() {
        return this.uri;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setErrorCode(int code) {
        this.errorCode = code;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public WeakReference<View> getView() {
        return view;
    }
}
