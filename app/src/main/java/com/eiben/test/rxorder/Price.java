package com.eiben.test.rxorder;

import java.io.Serializable;

/**
 * Created by liumingrui on 16/9/24.
 */

public class Price implements IData, Serializable {
    private int type;
    private String data;
    private String url;

    public Price(String url) {
        this.url = url;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }
}
