package com.eiben.asyncloader.base;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by liumingrui on 16/9/25.
 */

public abstract class BaseTask implements ITask {

    private IData data;

    @Override
    public IData getDataSource() {
        return data;
    }

    public BaseTask(IData data) {
        this.data = data;
    }

    @Override
    public LoadUri getUri() {
        return data.getUri();
    }

    @Override
    public void setData(String data) {
        this.data.setData(data);
    }

    @Override
    public String getData() {
        return this.data.getData();
    }

    @Override
    public void setErrorCode(int code) {
        this.data.setErrorCode(code);
    }

    @Override
    public int getErrorCode() {
        return this.data.getErrorCode();
    }

    @Override
    public WeakReference<View> getView() {
        return this.data.getView();
    }
}
