package com.eiben.test.rxorder;

/**
 * Created by liumingrui on 16/9/23.
 */

public abstract class Data implements IData {

    protected String url;
    protected String data = "";
    protected int errorCode = 0;

    public Data(String url) {
        this.url = url;
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
